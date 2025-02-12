package com.example.mdd

data class Question(val rightAnswer: String, val wrongAnswers: List<String>, val audioFile:String){
    fun getAllAnswers(): List<String> {
        return(wrongAnswers + rightAnswer)
    }
}