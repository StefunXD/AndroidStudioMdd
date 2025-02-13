package com.example.mdd.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SliderViewModel : ViewModel() {
    private val _sliderValue = MutableStateFlow(0)
    val sliderValue: StateFlow<Int> = _sliderValue

    fun updateSliderValue(newValue: Int) {
        _sliderValue.value = newValue
    }
}