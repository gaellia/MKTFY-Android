package com.launchpad.mktfy_android.ui.screens.login

import com.launchpad.mktfy_android.core.ActionViewModel

class LoginViewModel: ActionViewModel<LoginViewState, LoginAction>(LoginViewState()) {
    override fun collectAction(action: LoginAction) {
        when(action) {
            LoginAction.Login -> { }
            LoginAction.NavigateCreateAccount -> { }
            LoginAction.NavigateForgotPassword -> { }
            LoginAction.NavigateHome -> { }
            is LoginAction.UpdateEmail -> { }
            is LoginAction.UpdatePassword -> { }
        }
    }
}