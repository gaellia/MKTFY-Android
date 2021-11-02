package com.launchpad.mktfy_android.ui.screens.dashboard

import com.launchpad.mktfy_android.core.ActionViewModel

class DashboardViewModel :
    ActionViewModel<DashboardViewState, DashboardAction>(DashboardViewState()) {
    override fun collectAction(action: DashboardAction) {
        when (action) {
            DashboardAction.NavigateCreateListing -> { }
            DashboardAction.NavigateMenu -> { }
            DashboardAction.NavigateProductDetail -> { }
        }
    }
}