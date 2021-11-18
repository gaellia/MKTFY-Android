package com.launchpad.mktfy_android.ui.screens.changePassword


sealed class ChangePasswordAction {
    data class UpdateCurrentPasswordField(val currentPassword: String): ChangePasswordAction()
    data class UpdatePasswordField(val password: String): ChangePasswordAction()
    data class UpdateConfirmPasswordField(val confirmPassword: String): ChangePasswordAction()

    object UpdatePassword: ChangePasswordAction()
    object NavigateBack: ChangePasswordAction()

    object ShowCurrentPassword: ChangePasswordAction()
    object ShowPassword: ChangePasswordAction()
    object ShowConfirmPassword: ChangePasswordAction()
}