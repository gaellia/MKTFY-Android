package com.launchpad.mktfy_android.ui.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class DashboardViewModel :
    ActionViewModel<DashboardViewState, DashboardAction>(DashboardViewState()) {
    override fun collectAction(action: DashboardAction) {
        when (action) {
            DashboardAction.NavigateCreateListing -> { }
            DashboardAction.NavigateMenu -> { }
            DashboardAction.NavigateProductDetail -> { }
            is DashboardAction.UpdateSearch -> updateSearch(action.search)
            is DashboardAction.ChangeCategory -> changeCategory(action.category)
        }
    }

    private fun updateSearch(v: String) {
        viewModelScope.launchSetState { copy(search = v) }
    }

    private fun changeCategory(c: Category) {
        viewModelScope.launchSetState { copy(category = c) }
    }
}