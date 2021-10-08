package com.launchpad.mktfy_android.ui.screens.forgotPassword

import com.launchpad.mktfy_android.models.LoadState

sealed class ForgotPasswordAction {
    data class UpdateEmail(val email: String): ForgotPasswordAction()
    data class UpdateVerificationCode(val verificationCode: String): ForgotPasswordAction()
    data class UpdateEmailState(val LoadState: LoadState): ForgotPasswordAction()
    data class UpdateVerifyState(val LoadState: LoadState): ForgotPasswordAction()
    object ShowEmailField: ForgotPasswordAction()
    object NavigateLogin: ForgotPasswordAction()
    object NavigateResetPassword: ForgotPasswordAction()
}
