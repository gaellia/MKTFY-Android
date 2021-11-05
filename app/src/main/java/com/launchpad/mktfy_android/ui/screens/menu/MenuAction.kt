package com.launchpad.mktfy_android.ui.screens.menu

sealed class MenuAction {
    object NavigateBack: MenuAction()
    object NavigateAccountInfo: MenuAction()
    object NavigateChangePassword: MenuAction()
    object NavigateMyPurchases: MenuAction()
    object NavigateMyListings: MenuAction()
    object NavigateNotifications: MenuAction()
    object NavigateFAQ: MenuAction()
    object NavigateContactUs: MenuAction()
    object SignOut: MenuAction()
}