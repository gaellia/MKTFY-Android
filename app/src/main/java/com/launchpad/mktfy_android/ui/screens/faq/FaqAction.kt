package com.launchpad.mktfy_android.ui.screens.faq

sealed class FaqAction {
    object NavigateBack: FaqAction()
    data class NavigateAnswer(val selectedQuestion: Question): FaqAction()
    object NavigateQuestions: FaqAction()
}