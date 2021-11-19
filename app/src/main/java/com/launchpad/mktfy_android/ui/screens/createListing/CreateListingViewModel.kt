package com.launchpad.mktfy_android.ui.screens.createListing

import com.launchpad.mktfy_android.core.ActionViewModel

class CreateListingViewModel :
    ActionViewModel<CreateListingViewState, CreateListingAction>(CreateListingViewState()) {
    override fun collectAction(action: CreateListingAction) {
        when (action) {
            CreateListingAction.NavigateBack -> { }
            CreateListingAction.ToggleCategoryMenu -> { }
            CreateListingAction.ToggleConditionMenu -> { }
            CreateListingAction.ToggleCityMenu -> { }
            is CreateListingAction.UpdateAddress -> { }
            is CreateListingAction.UpdateCategory -> { }
            is CreateListingAction.UpdateCity -> { }
            is CreateListingAction.UpdateCondition -> { }
            is CreateListingAction.UpdateDescription -> { }
            is CreateListingAction.UpdatePrice -> { }
            is CreateListingAction.UpdateProductName -> { }
        }
    }
}