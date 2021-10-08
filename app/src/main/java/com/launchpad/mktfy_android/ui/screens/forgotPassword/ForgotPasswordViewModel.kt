package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class ForgotPasswordViewModel: ActionViewModel<ForgotPasswordViewState, ForgotPasswordAction>(ForgotPasswordViewState()) {
    override fun collectAction(action: ForgotPasswordAction) {
        when(action) {
            ForgotPasswordAction.ShowEmailField -> showEmailField()
            is ForgotPasswordAction.UpdateEmail -> updateEmail(action.email)
            is ForgotPasswordAction.UpdateVerificationCode -> updateVerificationCode(action.verificationCode)
            is ForgotPasswordAction.UpdateEmailState -> {}
            is ForgotPasswordAction.UpdateVerifyState -> {}
            ForgotPasswordAction.NavigateLogin -> { }
            ForgotPasswordAction.NavigateResetPassword -> {}
        }
    }

    fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
    }

    fun updateVerificationCode(v: String) {
        if (v.length <= 6) {
            viewModelScope.launchSetState { copy(verificationCode = v) }
        }
    }

    fun showEmailField() {
        viewModelScope.launchSetState { copy(showEmailField = !showEmailField) }
    }
}