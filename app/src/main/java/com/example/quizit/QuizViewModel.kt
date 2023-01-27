package com.example.quizit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizit.Data.QuizRepository
import com.example.quizit.Data.model.Question

class QuizViewModel : ViewModel() {

    private val repository = QuizRepository()
    private val questions = repository.loadQuestions()

    private var _totalscore = MutableLiveData<Int>(0)
    val totalscore: LiveData<Int>
    get() = _totalscore


    private var _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
    get() = _score

    private var _currentQuestion = MutableLiveData<Question>(questions.random())
    val currentQuestion: LiveData<Question>
    get() = _currentQuestion

    private val _gameEnd = MutableLiveData<Boolean>(false)
    val gameEnd: LiveData<Boolean>
    get() = _gameEnd

    fun checkAnswer(isMusician:Boolean) {

        if (_totalscore.value == 4) {
            _gameEnd.value = true
        }

        if (isMusician== currentQuestion.value?.isMusician) {
            _score.value = _score.value?.plus(1)
            _totalscore.value= _totalscore.value?.plus(1)
            getNextQuestion()

        }
        else {
            _totalscore.value = _totalscore.value?.plus(1)
            getNextQuestion()
        }
    }

    private fun getNextQuestion() {
        val nextQuestion = questions.random()

        if (nextQuestion== currentQuestion.value) {
            getNextQuestion()

        } else {
            _currentQuestion.value = nextQuestion
        }

    }
    fun restartGame() {
        _score.value = 0
        _totalscore.value = 0
        getNextQuestion()
        _gameEnd.value = false

    }

}