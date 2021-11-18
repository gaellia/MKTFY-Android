package com.launchpad.mktfy_android.ui.screens.faq

sealed class FaqAction {
    object NavigateBack: FaqAction()
    data class NavigateAnswer(val questionId: Int): FaqAction()
    object NavigateQuestions: FaqAction()
}