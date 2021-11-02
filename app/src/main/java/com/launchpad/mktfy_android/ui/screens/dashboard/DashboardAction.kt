package com.launchpad.mktfy_android.ui.screens.dashboard

sealed class DashboardAction {
    object NavigateMenu: DashboardAction()
    object NavigateCreateListing: DashboardAction()
    object NavigateProductDetail: DashboardAction()
}