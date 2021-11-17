package com.launchpad.mktfy_android.ui.screens.changePassword

data class ChangePasswordViewState(
    val currentPassword: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val atLeastSixChars: Boolean = false,
    val hasOneUppercase: Boolean = false,
    val hasOneNumber: Boolean = false,

    val showCurrentPassword: Boolean = false,
    val showPassword: Boolean = false,
    val showConfirmPassword: Boolean = false,

    val currentPasswordErrorState: Boolean = false
)