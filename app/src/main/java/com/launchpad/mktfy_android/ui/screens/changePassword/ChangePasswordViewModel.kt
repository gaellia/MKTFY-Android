package com.launchpad.mktfy_android.ui.screens.changePassword

import com.launchpad.mktfy_android.core.ActionViewModel

class ChangePasswordViewModel :
    ActionViewModel<ChangePasswordViewState, ChangePasswordAction>(ChangePasswordViewState()) {
    override fun collectAction(action: ChangePasswordAction) {
        when (action) {
            is ChangePasswordAction.UpdateConfirmPasswordField -> {}
            is ChangePasswordAction.UpdateCurrentPasswordField -> {}
            ChangePasswordAction.UpdatePassword -> {}
            is ChangePasswordAction.UpdatePasswordField -> {}
            ChangePasswordAction.NavigateBack -> {}
        }
    }
}