package com.launchpad.mktfy_android.ui.screens.accountInformation


sealed class AccountInformationAction {
    object NavigateBack: AccountInformationAction()
    object SaveAccountInformation: AccountInformationAction()

    data class UpdateFirstName(val firstName: String): AccountInformationAction()
    data class UpdateLastName(val lastName: String): AccountInformationAction()
    data class UpdatePhone(val phone: String): AccountInformationAction()
    data class UpdateCountry(val country: String): AccountInformationAction()
    data class UpdateCity(val city: String): AccountInformationAction()
    data class UpdateProvince(val province: String): AccountInformationAction()
    data class UpdateAddress(val address: String): AccountInformationAction()
}