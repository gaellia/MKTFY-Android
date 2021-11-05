package com.launchpad.mktfy_android.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import com.launchpad.mktfy_android.models.LoadState

class LoginViewModel: ActionViewModel<LoginViewState, LoginAction>(LoginViewState()) {
    override fun collectAction(action: LoginAction) {
        when(action) {
            LoginAction.Login -> { }
            LoginAction.NavigateCreateAccount -> { }
            LoginAction.NavigateForgotPassword -> { }
            LoginAction.NavigateDash -> { }
            is LoginAction.UpdateEmail -> updateEmail(action.email)
            is LoginAction.UpdatePassword -> updatePassword(action.password)
            LoginAction.ShowPassword -> toggleShowPassword()
            is LoginAction.UpdateLoginState -> updateLoginState(action.LoadState)
            LoginAction.HideEmailError -> updateEmailError(false)
            LoginAction.ShowEmailError -> updateEmailError(true)
        }
    }

    private fun updateEmailError(b: Boolean) {
        viewModelScope.launchSetState { copy(showEmailError = b) }
    }

    private fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
    }

    private fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(password = v) }
    }

    fun animateLogin() {
        viewModelScope.launchSetState { copy(showSplashScreen = false) }
    }

    private fun toggleShowPassword() {
        viewModelScope.launchSetState { copy(showPassword = !showPassword) }
    }

    private fun updateLoginState(s: LoadState) {
        viewModelScope.launchSetState { copy(loginLoadState = s) }
    }
}