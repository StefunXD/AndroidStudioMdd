package com.example.mdd.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val discreteValues = listOf(2,3,4,5,10,15,20)
val valueRange = 0f..3f
val steps = discreteValues.size -1

@Composable
fun SliderDiscrete(){
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column(modifier = Modifier.padding(16.dp)) {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = valueRange,
            steps = steps
        )
        Text(text = "${sliderPosition.toInt()}")
    }
}
