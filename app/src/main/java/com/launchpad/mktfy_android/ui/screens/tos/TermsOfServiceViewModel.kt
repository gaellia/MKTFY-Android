package com.launchpad.mktfy_android.ui.screens.tos

import com.launchpad.mktfy_android.core.ActionViewModel

class TermsOfServiceViewModel :
    ActionViewModel<TermsOfServiceViewState, TermsOfServiceAction>(TermsOfServiceViewState()) {
    override fun collectAction(action: TermsOfServiceAction) {
        when (action) {
            TermsOfServiceAction.NavigateBack -> { }
        }
    }
}