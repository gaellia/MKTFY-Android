package com.launchpad.mktfy_android.ui.screens.createAccount

import com.launchpad.mktfy_android.models.LoadState

data class CreateAccountViewState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val country: String = "",
    val province: String = "",
    val city: String = "",
    val address: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val createAccountLoadState: LoadState = LoadState.NONE,
    val createAccountErrorState: Boolean = false,
    val showCreatePasswordScreen: Boolean = false,

    val atLeastSixChars: Boolean = false,
    val hasOneUppercase: Boolean = false,
    val hasOneNumber: Boolean = false,
    val isChecked: Boolean = false,

    val showPassword: Boolean = false,
    val showConfirmPassword: Boolean = false
)
