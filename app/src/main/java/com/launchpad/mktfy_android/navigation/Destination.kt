package com.launchpad.mktfy_android.navigation

sealed class Destination(val route: String){
    open fun sendRoute() = route

    object LoginGraph: Destination("loginGraph")
    object Login: Destination("login")
    object ForgotPassword: Destination("forgotPassword")
    object CreateAccount: Destination("createAccount")
    object Dashboard: Destination("dashboard")
    object Menu: Destination("menu")
    object ChangePassword: Destination("changePassword")
    object AccountInformation: Destination("accountInformation")
    object FAQ: Destination("faq")
    object CreateListing: Destination("createListing")
    object PrivacyPolicy: Destination("privacyPolicy")
    object TermsOfService: Destination("termsOfService")
}