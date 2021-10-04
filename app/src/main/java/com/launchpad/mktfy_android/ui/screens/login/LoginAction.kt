package com.launchpad.mktfy_android.ui.screens.login

sealed class LoginAction {
    data class UpdateEmail(val email: String): LoginAction()
    data class UpdatePassword(val password: String): LoginAction()
    data class ShowPassword(val showPassword: Boolean): LoginAction()
    object Login: LoginAction()
    object NavigateHome: LoginAction()
    object NavigateForgotPassword: LoginAction()
    object NavigateCreateAccount: LoginAction()
}
