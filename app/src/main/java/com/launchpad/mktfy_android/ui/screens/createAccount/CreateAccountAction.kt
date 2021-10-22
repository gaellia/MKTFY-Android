package com.launchpad.mktfy_android.ui.screens.createAccount

import com.launchpad.mktfy_android.models.LoadState
import com.launchpad.mktfy_android.ui.screens.forgotPassword.ForgotPasswordAction

sealed class CreateAccountAction {
    data class UpdateFirstName(val firstName: String): CreateAccountAction()
    data class UpdateLastName(val lastName: String): CreateAccountAction()
    data class UpdateEmail(val email: String): CreateAccountAction()
    data class UpdatePhone(val phone: String): CreateAccountAction()
    data class UpdateCountry(val country: String): CreateAccountAction()
    data class UpdateCity(val city: String): CreateAccountAction()
    data class UpdateProvince(val province: String): CreateAccountAction()
    data class UpdateAddress(val address: String): CreateAccountAction()
    data class UpdatePassword(val password: String): CreateAccountAction()
    data class UpdateConfirmPassword(val confirmPassword: String): CreateAccountAction()
    object ShowCreatePasswordScreen: CreateAccountAction()
    object HideCreatePasswordScreen: CreateAccountAction()
    object NavigateBack: CreateAccountAction()
    object NavigateHome: CreateAccountAction()
}
