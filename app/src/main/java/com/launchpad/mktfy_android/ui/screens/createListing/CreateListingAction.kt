package com.launchpad.mktfy_android.ui.screens.createListing

import com.launchpad.mktfy_android.models.City

sealed class CreateListingAction {
    object NavigateBack: CreateListingAction()

    data class UpdateProductName(val name: String): CreateListingAction()
    data class UpdateDescription(val desc: String): CreateListingAction()
    data class UpdateCategory(val category: ProductCategory): CreateListingAction()
    data class UpdateCondition(val condition: ProductCondition): CreateListingAction()
    data class UpdatePrice(val price: String): CreateListingAction()
    data class UpdateAddress(val address: String): CreateListingAction()
    data class UpdateCity(val city: City): CreateListingAction()

    object ToggleCategoryMenu: CreateListingAction()
    object ToggleConditionMenu: CreateListingAction()
    object ToggleCityMenu: CreateListingAction()

    object PostListing: CreateListingAction()

    //TODO: Choose pictures
    //TODO: Post listing
}