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
            LoginAction.NavigateHome -> { }
            is LoginAction.UpdateEmail -> updateEmail(action.email)
            is LoginAction.UpdatePassword -> updatePassword(action.password)
            LoginAction.ShowPassword -> toggleShowPassword()
            is LoginAction.UpdateLoginState -> updateLoginState(action.LoadState)
        }
    }

    fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
    }

    fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(password = v) }
    }

    fun animateLogin() {
        viewModelScope.launchSetState { copy(showSplashScreen = false) }
    }

    fun toggleShowPassword() {
        viewModelScope.launchSetState { copy(showPassword = !showPassword) }
    }

    fun updateLoginState(s: LoadState) {
        viewModelScope.launchSetState { copy(loginLoadState = s) }
    }
}