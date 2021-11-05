package com.launchpad.mktfy_android.ui.screens.login

import com.launchpad.mktfy_android.models.LoadState

sealed class LoginAction {
    data class UpdateEmail(val email: String): LoginAction()
    data class UpdatePassword(val password: String): LoginAction()
    data class UpdateLoginState(val LoadState: LoadState): LoginAction()
    object ShowEmailError: LoginAction()
    object HideEmailError: LoginAction()
    object ShowPassword: LoginAction()
    object Login: LoginAction()
    object NavigateDash: LoginAction()
    object NavigateForgotPassword: LoginAction()
    object NavigateCreateAccount: LoginAction()
}
