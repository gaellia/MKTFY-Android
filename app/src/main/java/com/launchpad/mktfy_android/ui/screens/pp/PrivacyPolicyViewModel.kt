package com.launchpad.mktfy_android.ui.screens.pp

import com.launchpad.mktfy_android.core.ActionViewModel

class PrivacyPolicyViewModel :
    ActionViewModel<PrivacyPolicyViewState, PrivacyPolicyAction>(PrivacyPolicyViewState()) {
    override fun collectAction(action: PrivacyPolicyAction) {
        when (action) {
            PrivacyPolicyAction.NavigateBack -> { }
        }
    }
}