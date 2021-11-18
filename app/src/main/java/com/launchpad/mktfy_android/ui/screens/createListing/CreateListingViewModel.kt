package com.launchpad.mktfy_android.ui.screens.createListing

import com.launchpad.mktfy_android.core.ActionViewModel

class CreateListingViewModel :
    ActionViewModel<CreateListingViewState, CreateListingAction>(CreateListingViewState()) {
    override fun collectAction(action: CreateListingAction) {
        when (action) {
            CreateListingAction.NavigateBack -> { }
            CreateListingAction.ToggleCategoryMenu -> TODO()
            CreateListingAction.ToggleConditionMenu -> TODO()
            is CreateListingAction.UpdateAddress -> TODO()
            is CreateListingAction.UpdateCategory -> TODO()
            is CreateListingAction.UpdateCity -> TODO()
            is CreateListingAction.UpdateCondition -> TODO()
            is CreateListingAction.UpdateDescription -> TODO()
            is CreateListingAction.UpdatePrice -> TODO()
            is CreateListingAction.UpdateProductName -> TODO()
        }
    }
}