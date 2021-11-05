package com.launchpad.mktfy_android.ui.screens.forgotPassword

import com.launchpad.mktfy_android.models.LoadState

data class ForgotPasswordViewState(
    val email: String = "",
    val emailLoadState: LoadState = LoadState.NONE,
    val showEmailError: Boolean = false
)
