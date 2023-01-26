package com.example.quizit.Data

import com.example.quizit.Data.model.Question

class QuizRepository {

    fun loadQuestions(): List<Question> {

        val questionList = listOf(
            Question("Kirk Franklin", true),
            Question("Jonathan Buttler",true),
        Question("Keith Sweat",true),
       Question("R Kelly",true),
        Question("P.Diddy",true),
        Question("Luis Suarez",false),
        Question("Oliver Kahn",false),
        Question("David Beckham",false),
        Question("Phillip Lahm",false),
        Question("Quinton Fortune",false)

        )
        return questionList.shuffled()

    }
}