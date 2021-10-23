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
            CreateAccountAction.NavigateHome -> { }
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

    fun updateAddress(v: String) {
        viewModelScope.launchSetState { copy(address = v) }
    }
    fun updateCity(v: String) {
        viewModelScope.launchSetState { copy(city = v) }
    }
    fun updateCountry(v: String) {
        viewModelScope.launchSetState { copy(country = v) }
    }
    fun updateEmail(v: String) {
        viewModelScope.launchSetState { copy(email = v) }
    }
    fun updateFirstName(v: String) {
        viewModelScope.launchSetState { copy(firstName = v) }
    }
    fun updateLastName(v: String) {
        viewModelScope.launchSetState { copy(lastName = v) }
    }
    fun updatePhone(v: String) {
        if (v.length <= 10 && v.isDigitsOnly()) {
            viewModelScope.launchSetState { copy(phone = v) }
        }
    }
    fun updateProvince(v: String) {
        viewModelScope.launchSetState { copy(province = v) }
    }

    fun setShowPasswordScreen(b: Boolean) {
        viewModelScope.launchSetState { copy(showCreatePasswordScreen = b) }
    }

    fun updatePassword(v: String) {
        viewModelScope.launchSetState { copy(
            password = v,
            hasOneUppercase = v.contains(regex = Regex("[A-Z]")),
            hasOneNumber = v.contains(regex = Regex("\\d")),
            atLeastSixChars = v.length >= 6)
        }
    }
    fun updateConfirmPassword(v: String) {
        viewModelScope.launchSetState { copy(confirmPassword = v) }
    }

    fun toggleShowPassword() {
        viewModelScope.launchSetState { copy(showPassword = !showPassword) }
    }
    fun toggleShowConfirmPassword() {
        viewModelScope.launchSetState { copy(showConfirmPassword = !showConfirmPassword) }
    }

    fun toggleTOSCheck() {
        viewModelScope.launchSetState { copy(isChecked = !isChecked) }
    }
}