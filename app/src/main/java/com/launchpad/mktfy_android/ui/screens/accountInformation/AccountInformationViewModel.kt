package com.launchpad.mktfy_android.ui.screens.accountInformation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class AccountInformationViewModel :
    ActionViewModel<AccountInformationViewState, AccountInformationAction>(
        AccountInformationViewState()
    ) {
    override fun collectAction(action: AccountInformationAction) {
        when (action) {
            AccountInformationAction.NavigateBack -> { }
            AccountInformationAction.SaveAccountInformation -> { /*TODO: Save info*/}
            is AccountInformationAction.UpdateAddress -> updateAddress(action.address)
            is AccountInformationAction.UpdateCity -> updateCity(action.city)
            is AccountInformationAction.UpdateCountry -> updateCountry(action.country)
            is AccountInformationAction.UpdateFirstName -> updateFirstName(action.firstName)
            is AccountInformationAction.UpdateLastName -> updateLastName(action.lastName)
            is AccountInformationAction.UpdatePhone -> updatePhone(action.phone)
            is AccountInformationAction.UpdateProvince -> updateProvince(action.province)
        }
    }

    private fun updateAddress(v: String) {
        viewModelScope.launchSetState { copy(address = v) }
    }
    private fun updateCity(v: String) {
        viewModelScope.launchSetState { copy(city = v) }
    }
    private fun updateCountry(v: String) {
        viewModelScope.launchSetState { copy(country = v) }
    }
    private fun updateFirstName(v: String) {
        viewModelScope.launchSetState { copy(firstName = v) }
    }
    private fun updateLastName(v: String) {
        viewModelScope.launchSetState { copy(lastName = v) }
    }
    private fun updatePhone(v: String) {
        if (v.length <= 10 && v.isDigitsOnly()) {
            viewModelScope.launchSetState { copy(phone = v) }
        }
    }
    private fun updateProvince(v: String) {
        viewModelScope.launchSetState { copy(province = v) }
    }
}