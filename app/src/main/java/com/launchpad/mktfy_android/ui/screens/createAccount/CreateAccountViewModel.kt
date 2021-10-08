package com.launchpad.mktfy_android.ui.screens.createAccount


import com.launchpad.mktfy_android.core.ActionViewModel

class CreateAccountViewModel: ActionViewModel<CreateAccountViewState, CreateAccountAction>(
    CreateAccountViewState()
) {
    override fun collectAction(action: CreateAccountAction) {
        when (action) {
            CreateAccountAction.NavigateBack -> { }
            CreateAccountAction.NavigateHome -> { }
            CreateAccountAction.ShowCreatePasswordScreen -> { }
            is CreateAccountAction.UpdateAddress -> { }
            is CreateAccountAction.UpdateCity -> { }
            is CreateAccountAction.UpdateCountry -> { }
            is CreateAccountAction.UpdateEmail -> { }
            is CreateAccountAction.UpdateFirstName -> { }
            is CreateAccountAction.UpdateLastName -> { }
            is CreateAccountAction.UpdatePassword -> { }
            is CreateAccountAction.UpdatePhone -> { }
        }
    }
}