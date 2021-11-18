package com.launchpad.mktfy_android.ui.screens.dashboard

sealed class DashboardAction {
    object NavigateMenu: DashboardAction()
    object NavigateCreateListing: DashboardAction()
    object NavigateProductDetail: DashboardAction()

    data class UpdateSearch(val search: String): DashboardAction()

    data class ChangeCategory(val category: Category): DashboardAction()
}