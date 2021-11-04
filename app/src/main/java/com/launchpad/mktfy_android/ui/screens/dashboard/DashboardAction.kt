package com.launchpad.mktfy_android.ui.screens.dashboard

import com.launchpad.mktfy_android.ui.screens.login.LoginAction

sealed class DashboardAction {
    object NavigateMenu: DashboardAction()
    object NavigateCreateListing: DashboardAction()
    object NavigateProductDetail: DashboardAction()

    data class UpdateSearch(val search: String): DashboardAction()

    data class ChangeCategory(val category: Category): DashboardAction()
}