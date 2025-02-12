package com.example.mdd.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelBlindTestOptionsDropdown : ViewModel() {
    private val _selectedOption = MutableStateFlow("Selectionner un réponse")
    val selectedOption: StateFlow<String> = _selectedOption.asStateFlow()

    fun updateSelectedOption(option: String) {
        _selectedOption.value = option

    }

}