package com.launchpad.mktfy_android.ui.screens.login

import com.launchpad.mktfy_android.models.LoadState

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val loginLoadState: LoadState = LoadState.NONE,
    val showEmailError: Boolean = false,
    val showSplashScreen: Boolean = true,
    val showPassword: Boolean = false
)
