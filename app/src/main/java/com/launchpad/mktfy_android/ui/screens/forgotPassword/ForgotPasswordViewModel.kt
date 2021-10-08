package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class ForgotPasswordViewModel: ActionViewModel<ForgotPasswordViewState, ForgotPasswordAction>(ForgotPasswordViewState()) {
    override fun collectAction(action: ForgotPasswordAction) {
        when(action) {
            is ForgotPasswordAction.UpdateEmail -> updateEmail(action.email)
            is ForgotPasswordAction.UpdateEmailState -> {}
            ForgotPasswordAction.NavigateBack -> { }
        }
    }

    fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
    }
}