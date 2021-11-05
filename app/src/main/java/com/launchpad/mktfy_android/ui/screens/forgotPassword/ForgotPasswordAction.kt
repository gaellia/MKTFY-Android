package com.launchpad.mktfy_android.ui.screens.forgotPassword

import com.launchpad.mktfy_android.models.LoadState

sealed class ForgotPasswordAction {
    data class UpdateEmail(val email: String): ForgotPasswordAction()
    data class UpdateEmailLoadState(val LoadState: LoadState): ForgotPasswordAction()
    object ShowEmailError: ForgotPasswordAction()
    object HideEmailError: ForgotPasswordAction()
    object NavigateBack: ForgotPasswordAction()
}
