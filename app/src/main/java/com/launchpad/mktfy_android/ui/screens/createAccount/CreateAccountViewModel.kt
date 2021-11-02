package com.launchpad.mktfy_android.ui.screens.createAccount


import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel

class CreateAccountViewModel: ActionViewModel<CreateAccountViewState, CreateAccountAction>(
    CreateAccountViewState()
) {
    override fun collectAction(action: CreateAccountAction) {
        when (action) {
            CreateAccountAction.NavigateBack -> { }
            CreateAccountAction.NavigateDash -> { }
            CreateAccountAction.NavigateTOS -> { }
            CreateAccountAction.NavigatePP -> { }
            CreateAccountAction.ShowCreatePasswordScreen -> setShowPasswordScreen(true)
            CreateAccountAction.HideCreatePasswordScreen -> setShowPasswordScreen(false)
            is CreateAccountAction.UpdateAddress -> updateAddress(action.address)
            is CreateAccountAction.UpdateCity -> updateCity(action.city)
            is CreateAccountAction.UpdateCountry -> updateCountry(action.country)
            is CreateAccountAction.UpdateEmail -> updateEmail(action.email)
            is CreateAccountAction.UpdateFirstName -> updateFirstName(action.firstName)
            is CreateAccountAction.UpdateLastName -> updateLastName(action.lastName)
            is CreateAccountAction.UpdatePassword -> updatePassword(action.password)
            is CreateAccountAction.UpdatePhone -> updatePhone(action.phone)
            is CreateAccountAction.UpdateConfirmPassword -> updateConfirmPassword(action.confirmPassword)
            is CreateAccountAction.UpdateProvince -> updateProvince(action.province)
            CreateAccountAction.ShowPassword -> toggleShowPassword()
            CreateAccountAction.ShowConfirmPassword -> toggleShowConfirmPassword()
            CreateAccountAction.UpdateTOSCheck -> toggleTOSCheck()
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
    private fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
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

    private fun setShowPasswordScreen(b: Boolean) {
        viewModelScope.launchSetState { copy(showCreatePasswordScreen = b) }
    }

    private fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(
            password = v,
            hasOneUppercase = v.contains(regex = Regex("[A-Z]")),
            hasOneNumber = v.contains(regex = Regex("\\d")),
            atLeastSixChars = v.length >= 6)
        }
    }
    private fun updateConfirmPassword(v: String) {
        viewModelScope.launchSetState { copy(confirmPassword = v) }
    }

    private fun toggleShowPassword() {
        viewModelScope.launchSetState { copy(showPassword = !showPassword) }
    }
    private fun toggleShowConfirmPassword() {
        viewModelScope.launchSetState { copy(showConfirmPassword = !showConfirmPassword) }
    }

    private fun toggleTOSCheck() {
        viewModelScope.launchSetState { copy(isChecked = !isChecked) }
    }
}