package com.launchpad.mktfy_android.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

@Composable
fun Login(
    navigateHome: () -> Unit,
    navigateForgotPassword: () -> Unit,
    navigateCreateAccount: () -> Unit
) {
    LoginState(navigateHome = navigateHome,
        navigateForgotPassword = navigateForgotPassword,
        navigateCreateAccount = navigateCreateAccount
    )
}

@Composable
fun LoginState(
    loginViewModel: LoginViewModel = viewModel(),
    navigateHome: () -> Unit,
    navigateForgotPassword: () -> Unit,
    navigateCreateAccount: () -> Unit
) {
    val viewState by loginViewModel.state.collectAsState()
    LoginContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                LoginAction.NavigateCreateAccount -> navigateCreateAccount()
                LoginAction.NavigateForgotPassword -> navigateForgotPassword()
                LoginAction.NavigateHome -> navigateHome()
                else -> loginViewModel.collectAction(action)
            }
        }
    )
}

@Composable
fun LoginContent(
    viewState: LoginViewState = LoginViewState(),
    actioner: (LoginAction) -> Unit = {}
) {

}

@Preview
@Composable
fun SplashPreview() {
    MKTFY_AndroidTheme {
        LoginContent()
    }
}

@Preview
@Composable
fun LoginPreview() {
    MKTFY_AndroidTheme {
        LoginContent(LoginViewState(showSplashScreen = false))
    }
}