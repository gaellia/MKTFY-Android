package com.launchpad.mktfy_android.ui.screens.forgotPassword

import com.launchpad.mktfy_android.models.LoadState

data class ForgotPasswordViewState(
    val email: String = "",
    val verificationCode: String = "",
    val emailState: LoadState = LoadState.NONE,
    val verifyState: LoadState = LoadState.NONE,
    val showEmailField: Boolean = true
)
