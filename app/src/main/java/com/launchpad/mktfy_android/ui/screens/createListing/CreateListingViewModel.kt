package com.launchpad.mktfy_android.ui.screens.createListing

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import com.launchpad.mktfy_android.models.City

class CreateListingViewModel :
    ActionViewModel<CreateListingViewState, CreateListingAction>(CreateListingViewState()) {
    override fun collectAction(action: CreateListingAction) {
        when (action) {
            CreateListingAction.NavigateBack -> { }
            CreateListingAction.ToggleCategoryMenu -> toggleCategoryMenu()
            CreateListingAction.ToggleConditionMenu -> toggleConditionMenu()
            CreateListingAction.ToggleCityMenu -> toggleCityMenu()
            is CreateListingAction.UpdateAddress -> updateAddress(action.address)
            is CreateListingAction.UpdateCategory -> updateCategory(action.category)
            is CreateListingAction.UpdateCity -> updateCity(action.city)
            is CreateListingAction.UpdateCondition -> updateCondition(action.condition)
            is CreateListingAction.UpdateDescription -> updateDesc(action.desc)
            is CreateListingAction.UpdatePrice -> updatePrice(action.price)
            is CreateListingAction.UpdateProductName -> updateProductName(action.name)
            CreateListingAction.PostListing -> { }
        }
    }

    private fun toggleCategoryMenu() {
        viewModelScope.launchSetState { copy(expandedCategoryMenu = !expandedCategoryMenu) }
    }

    private fun toggleConditionMenu() {
        viewModelScope.launchSetState { copy(expandedConditionMenu = !expandedConditionMenu) }
    }

    private fun toggleCityMenu() {
        viewModelScope.launchSetState { copy(expandedCityMenu = !expandedCityMenu) }
    }

    private fun updateAddress(a: String){
        viewModelScope.launchSetState { copy(address = a) }
    }

    private fun updateCategory(c: ProductCategory){
        viewModelScope.launchSetState { copy(category = c) }
    }

    private fun updateCity(c: City){
        viewModelScope.launchSetState { copy(city = c) }
    }

    private fun updateCondition(c: ProductCondition){
        viewModelScope.launchSetState { copy(condition = c) }
    }

    private fun updateDesc(d: String){
        viewModelScope.launchSetState { copy(description = d) }
    }

    private fun updatePrice(p: String){
        viewModelScope.launchSetState { copy(price = p) }
    }

    private fun updateProductName(p: String){
        viewModelScope.launchSetState { copy(productName = p) }
    }
}