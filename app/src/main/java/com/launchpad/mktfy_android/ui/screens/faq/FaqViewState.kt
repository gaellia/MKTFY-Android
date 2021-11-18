package com.launchpad.mktfy_android.ui.screens.faq

data class FaqViewState(
    val selectedQuestion: Question? = null,
    val showAnswer: Boolean = false,

    val questions: List<Question> = listOf()
)

data class Question(
    val id: Int,
    val question: String,
    val answer: String
)