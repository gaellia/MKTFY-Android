package com.launchpad.mktfy_android.ui.screens.menu

import com.launchpad.mktfy_android.core.ActionViewModel

class MenuViewModel : ActionViewModel<MenuViewState, MenuAction>(MenuViewState()) {
    override fun collectAction(action: MenuAction) {
        when (action) {
            MenuAction.NavigateAccountInfo -> { }
            MenuAction.NavigateBack -> { }
            MenuAction.NavigateChangePassword -> { }
            MenuAction.NavigateContactUs -> { }
            MenuAction.NavigateFAQ -> { }
            MenuAction.NavigateMyListings -> { }
            MenuAction.NavigateMyPurchases -> { }
            MenuAction.NavigateNotifications -> { }
            MenuAction.SignOut -> { }
        }
    }
}