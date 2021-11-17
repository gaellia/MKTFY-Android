package com.launchpad.mktfy_android.ui.screens.changePassword

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import com.launchpad.mktfy_android.ui.screens.createAccount.CreateAccountAction

class ChangePasswordViewModel :
    ActionViewModel<ChangePasswordViewState, ChangePasswordAction>(ChangePasswordViewState()) {
    override fun collectAction(action: ChangePasswordAction) {
        when (action) {
            is ChangePasswordAction.UpdateConfirmPasswordField -> updateConfirmPassword(action.confirmPassword)
            is ChangePasswordAction.UpdateCurrentPasswordField -> updateCurrentPassword(action.currentPassword)
            ChangePasswordAction.UpdatePassword -> { /*TODO: Update Password request*/}
            is ChangePasswordAction.UpdatePasswordField -> updatePassword(action.password)
            ChangePasswordAction.NavigateBack -> {}
            ChangePasswordAction.ShowConfirmPassword -> toggleShowConfirmPassword()
            ChangePasswordAction.ShowCurrentPassword -> toggleShowCurrentPassword()
            ChangePasswordAction.ShowPassword -> toggleShowPassword()
        }
    }

    private fun updateCurrentPassword(v: String) {
        viewModelScope.launchSetState { copy(currentPassword = v) }
    }

    private fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(
            password = v,
            hasOneUppercase = v.contains(regex = Regex("[A-Z]")),
            hasOneNumber = v.contains(regex = Regex("\\d")),
            atLeastSixChars = v.length >= 6)
        }
    }
    private fun updateConfirmPassword(v: String) {
        viewModelScope.launchSetState { copy(confirmPassword = v) }
    }

    private fun toggleShowPassword() {
        viewModelScope.launchSetState { copy(showPassword = !showPassword) }
    }
    private fun toggleShowConfirmPassword() {
        viewModelScope.launchSetState { copy(showConfirmPassword = !showConfirmPassword) }
    }
    private fun toggleShowCurrentPassword() {
        viewModelScope.launchSetState { copy(showCurrentPassword = !showConfirmPassword) }
    }
}