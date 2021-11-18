package com.launchpad.mktfy_android.ui.screens.faq

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class FaqViewModel : ActionViewModel<FaqViewState, FaqAction>(FaqViewState()) {
    override fun collectAction(action: FaqAction) {
        when (action) {
            is FaqAction.NavigateAnswer -> navigateAnswer(action.questionId)
            FaqAction.NavigateBack -> { }
            FaqAction.NavigateQuestions -> hideAnswer()
        }
    }

    private fun navigateAnswer(q: Int){
        viewModelScope.launchSetState { copy(questionId = q, showAnswer = true) }
    }

    private fun hideAnswer() {
        viewModelScope.launchSetState { copy(showAnswer = false) }
    }
}