package com.example.mdd.viewmodel


import android.content.Context
import android.os.CountDownTimer
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mdd.model.DataBlindTest
import com.example.mdd.model.DataBlindTestAnwser
import com.example.mdd.model.DataBlindTestQuestions
import com.example.mdd.utils.BlindTestQuestionsJsonUtils
import com.example.mdd.utils.BlindTestQuestionsJsonUtils.loadQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class BlindTestViewModel : ViewModel() {
    private var time: CountDownTimer? = null
    private var currentQuestionCount = 0
    private var questions: List<DataBlindTestQuestions> = emptyList()

    private val _blindTest = MutableStateFlow<DataBlindTest?>(null)
    val blindTest: StateFlow<DataBlindTest?> = _blindTest.asStateFlow()

    private val _allQuestions = MutableStateFlow<List<DataBlindTestQuestions>?>(emptyList())
    val allQuestions: StateFlow<List<DataBlindTestQuestions>?> = _allQuestions.asStateFlow()

    private val _currentQuestion = MutableStateFlow<DataBlindTestQuestions?>(null)
    val currentQuestion: StateFlow<DataBlindTestQuestions?> = _currentQuestion.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _isCorrectAnswer = MutableStateFlow<Boolean?>(null)
    val isCorrectAnswer: StateFlow<Boolean?> = _isCorrectAnswer
    
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _timerValue = MutableStateFlow(0)
    val timer: StateFlow<Int> = _timerValue

    private val _isQuizStarted = MutableStateFlow(false)
    val isQuizStarted: StateFlow<Boolean> = _isQuizStarted.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    private val _selectedGenre = MutableStateFlow<String?>(null)
    val selectedGenre: StateFlow<String?> = _selectedGenre.asStateFlow()

    private val _selectedArtiste = MutableStateFlow<String?>(null)
    val selectedArtiste: StateFlow<String?> = _selectedArtiste.asStateFlow()

    private val _selectedOption = MutableStateFlow<String>("general")
    val selectedOption: StateFlow<String> = _selectedOption.asStateFlow()

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }



    fun loadBlindTestView(context: Context) {
        viewModelScope.launch {
            questions = loadQuestions(context)
        }
        startTimer()
    }

    private fun startTimer(){
        time?.cancel()
        time = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerValue.value = (millisUntilFinished / 1000).toInt()

            }

            override fun onFinish() {
               _timerValue.value = 0
                //TODO : a changer mettre une autre page
                nextQuestion()
            }
        }.start()
    }

 /*   fun loadBlindTestView(context: Context) {
       _blindTest.value = loadBlindTestData(context)
        Log.d("BlindTestViewModel", "BlindTest loaded: ${_blindTest.value != null}")
}*/
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

    fun startBlindTestWithQuestion(question: DataBlindTestQuestions) {
        _allQuestions.value = listOf(question)
        _currentQuestionIndex.value = 0

        _score.value = 0
        _currentQuestion.value = _allQuestions.value!!.getOrNull(_currentQuestionIndex.value)
    }

    //Si artist est selectionner
    fun selectArtist(artist: String) {
        _selectedArtiste.value = artist
        _currentQuestionIndex.value = 0
        _score.value = 0
        val filteredQuestions = _blindTest.value?.questions?.filter { it.artiste == artist }
        _currentQuestion.value = filteredQuestions?.shuffled()?.firstOrNull()
    }

    fun nextQuestion() {
        time?.cancel()
        currentQuestionCount++
        if (currentQuestionCount < questions.size) {
            _currentQuestion.value = questions[currentQuestionCount]
            startTimer()
            /*Log.d("BlindTestViewModel", "Current question: ${_currentQuestionIndex.value}")
            _currentQuestion.value = _allQuestions.value?.getOrNull(nextIndex)*/
        }
        else {
            Log.d("BlindTestViewModel", "Blind test finished!")
           /*_currentQuestion.value = null*/
        }
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

    fun checkAnswer(answer:DataBlindTestAnwser) {
        _isCorrectAnswer.value = answer.isCorrect
    }

    /*fun checkAnswer(selectedAnswer: String) {
        val question = _currentQuestion.value ?: return
        val correctAnswer = question.answers.find { it.isCorrect }?.text
        if (selectedAnswer == correctAnswer) {
            _score.value++
        }
    }*/

    fun updateSelectedOption(option: String) {
       _selectedOption.value = option
    }

}