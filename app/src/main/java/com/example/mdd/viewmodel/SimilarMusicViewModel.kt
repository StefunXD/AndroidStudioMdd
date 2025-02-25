package com.example.mdd.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mdd.model.SimilarMusic
import com.example.mdd.utils.SimilarPageJsonUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class SimilarMusicViewModel : ViewModel() {
    private val _similarMusicList = MutableStateFlow<List<SimilarMusic>>(emptyList())
    val similarMusicList: StateFlow<List<SimilarMusic>> = _similarMusicList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadSimilarMusicData(context: Context) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val inputStream = context.assets.open("similar_music.json") // Replace with your file name
                val data = SimilarPageJsonUtils.parseSimilarMusicData(inputStream)
                if (data != null) {
                    _similarMusicList.value = data.map { formatDates(it)!! }
                } else {
                    _error.value = "Failed to parse data"
                }
            } catch (e: Exception) {
                _error.value = "Error loading data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDates(similarMusic: SimilarMusic): SimilarMusic? {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy",
            Locale.getDefault())
        val dateCreationFormatted = similarMusic.dateCreation.format(formatter)
        return (if (dateCreationFormatted != null) LocalDateTime.parse(dateCreationFormatted, formatter) else null)?.let {
            similarMusic.date1.let { formatDateString(it) }.let { it1 ->
                similarMusic.date2.let { formatDateString(it) }.let { it2 ->
                    similarMusic.date.let { formatDateString(it) }.let { it3 ->
                        similarMusic.copy(
                            dateCreation = it,
                            date1 = it1,
                            date2 = it2,
                            date = it3
                        )
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatDateString(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",
            Locale.getDefault())
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy",
            Locale.getDefault())
        return try {
            val date = java.time.LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (e: Exception) {
            dateString
        }
    }
}