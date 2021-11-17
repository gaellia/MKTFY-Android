package com.launchpad.mktfy_android.ui.screens.accountInformation

import com.launchpad.mktfy_android.core.ActionViewModel

class AccountInformationViewModel :
    ActionViewModel<AccountInformationViewState, AccountInformationAction>(
        AccountInformationViewState()
    ) {
    override fun collectAction(action: AccountInformationAction) {
        when (action) {
            AccountInformationAction.NavigateBack -> { }
        }
    }
}