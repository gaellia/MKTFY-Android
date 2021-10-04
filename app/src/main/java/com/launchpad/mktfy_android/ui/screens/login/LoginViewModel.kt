package com.launchpad.mktfy_android.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class LoginViewModel: ActionViewModel<LoginViewState, LoginAction>(LoginViewState()) {
    override fun collectAction(action: LoginAction) {
        when(action) {
            LoginAction.Login -> { }
            LoginAction.NavigateCreateAccount -> { }
            LoginAction.NavigateForgotPassword -> { }
            LoginAction.NavigateHome -> { }
            is LoginAction.UpdateEmail -> { }
            is LoginAction.UpdatePassword -> updatePassword(action.password)
            is LoginAction.ShowPassword -> toggleShowPassword(action.showPassword)
        }
    }

    fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(password = v) }
    }

    fun animateLogin() {
        viewModelScope.launchSetState { copy(showSplashScreen = false) }
    }

    fun toggleShowPassword(t: Boolean) {
        viewModelScope.launchSetState { copy(showPassword = !t) }
    }
}