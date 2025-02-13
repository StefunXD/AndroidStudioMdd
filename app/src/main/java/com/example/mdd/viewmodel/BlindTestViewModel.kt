package com.example.mdd.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.mdd.model.DataBlindTestQuestion.DataBlindTest
import com.example.mdd.model.DataBlindTestQuestion.DataBlindTestQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.mdd.utils.BlindTestJsonUtils as BlindTestJsonUtils1

class BlindTestViewModel : ViewModel() {
    private val _blindTest = MutableStateFlow<DataBlindTest?>(null)
    val blindTest: StateFlow<DataBlindTest?> = _blindTest.asStateFlow()

    private val _allQuestions = MutableStateFlow<List<DataBlindTestQuestions>?>(emptyList())
    val allQuestions: StateFlow<List<DataBlindTestQuestions>?> = _allQuestions.asStateFlow()

    private val _currentQuestion = MutableStateFlow<DataBlindTestQuestions?>(null)
    val currentQuestion: StateFlow<DataBlindTestQuestions?> = _currentQuestion.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()
    
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _isQuizStarted = MutableStateFlow(false)
    val isQuizStarted: StateFlow<Boolean> = _isQuizStarted.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _selectedGenre = MutableStateFlow<String?>(null)
    val selectedGenre: StateFlow<String?> = _selectedGenre.asStateFlow()

    private val _selectedArtiste = MutableStateFlow<String?>(null)
    val selectedArtiste: StateFlow<String?> = _selectedArtiste.asStateFlow()

    fun loadBlindTest(context: Context) {
        BlindTestJsonUtils1.loadBlindTestQuestions(context)
}
    fun startBlindTest(){
        _allQuestions.value = _blindTest.value?.questions?.shuffled() ?: emptyList()
        _currentQuestionIndex.value = 0
        _score.value = 0
        _currentQuestion.value = _allQuestions.value?.getOrNull(_currentQuestionIndex.value)
    }
    //Si genre est selectionner
    fun selectGenre(genre: String) {
        _selectedGenre.value = genre
        _currentQuestionIndex.value = 0
        _score.value = 0
        val filteredQuestions = _blindTest.value?.questions?.filter { it.genre == genre }
        _currentQuestion.value = filteredQuestions?.shuffled()?.firstOrNull()
    }
    //Si artist est selectionner
    fun selectArtist(artist: String) {
        _selectedArtiste.value = artist
        _currentQuestionIndex.value = 0
        _score.value = 0
        val filteredQuestions = _blindTest.value?.questions?.filter { it.artiste == artist }
        _currentQuestion.value = filteredQuestions?.shuffled()?.firstOrNull()
    }

    fun nextQuestionGenre() {
        val genre = _selectedGenre.value ?: return
        val filteredQuestions = _blindTest.value?.questions?.filter { it.genre == genre }
        val nextIndex = _currentQuestionIndex.value + 1
        if (nextIndex < (filteredQuestions?.size ?: 0) && nextIndex < 4) {
            _currentQuestionIndex.value = nextIndex
            _currentQuestion.value = filteredQuestions?.shuffled()?.getOrNull(nextIndex)
        } else {
            _currentQuestion.value = null
        }
    }

    fun nextQuestionArtist() {
        val artist = _selectedArtiste.value ?: return
        val filteredQuestions = _blindTest.value?.questions?.filter { it.artiste == artist }
        val nextIndex = _currentQuestionIndex.value + 1
        if (nextIndex < (filteredQuestions?.size ?: 0) && nextIndex < 4) {
            _currentQuestionIndex.value = nextIndex
            _currentQuestion.value = filteredQuestions?.shuffled()?.getOrNull(nextIndex)
        } else {
            _currentQuestion.value = null
        }
    }

    fun checkAnswer(selectedAnswer: String) {
        val question = _currentQuestion.value ?: return
        val correctAnswer = question.answers.find { it.isCorrect }?.text
        if (selectedAnswer == correctAnswer) {
            _score.value++
        }
    }

}