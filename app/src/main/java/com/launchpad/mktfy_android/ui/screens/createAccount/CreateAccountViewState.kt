package com.launchpad.mktfy_android.ui.screens.createAccount

import com.launchpad.mktfy_android.models.LoadState

data class CreateAccountViewState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val country: String = "",
    val city: String = "",
    val address: String = "",
    val password: String = "",
    val createAccountLoadState: LoadState = LoadState.NONE,
    val showCreatePasswordScreen: Boolean = false
)
