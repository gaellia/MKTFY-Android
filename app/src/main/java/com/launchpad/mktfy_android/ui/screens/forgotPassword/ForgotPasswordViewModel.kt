package com.launchpad.mktfy_android.ui.screens.forgotPassword

import com.launchpad.mktfy_android.core.ActionViewModel

class ForgotPasswordViewModel: ActionViewModel<ForgotPasswordViewState, ForgotPasswordAction>(ForgotPasswordViewState()) {
    override fun collectAction(action: ForgotPasswordAction) {
        when(action) {
            ForgotPasswordAction.ShowEmailField -> {}
            is ForgotPasswordAction.UpdateEmail -> {}
            is ForgotPasswordAction.UpdateVerificationCode -> {}
            is ForgotPasswordAction.UpdateVerifyState -> {}
        }
    }
}