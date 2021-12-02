package com.launchpad.mktfy_android.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.launchpad.mktfy_android.ui.screens.accountInformation.AccountInformation
import com.launchpad.mktfy_android.ui.screens.changePassword.ChangePassword
import com.launchpad.mktfy_android.ui.screens.createAccount.CreateAccount
import com.launchpad.mktfy_android.ui.screens.createListing.CreateListing
import com.launchpad.mktfy_android.ui.screens.dashboard.Dashboard
import com.launchpad.mktfy_android.ui.screens.faq.Faq
import com.launchpad.mktfy_android.ui.screens.forgotPassword.ForgotPassword
import com.launchpad.mktfy_android.ui.screens.login.Login
import com.launchpad.mktfy_android.ui.screens.menu.Menu
import com.launchpad.mktfy_android.ui.screens.pp.PrivacyPolicy
import com.launchpad.mktfy_android.ui.screens.tos.TermsOfService

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.LoginGraph.route){
        addLoginNavigation(navController)
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun NavGraphBuilder.addLoginNavigation(navController: NavHostController){
    navigation(
        startDestination = Destination.Login.route,
        route = Destination.LoginGraph.route
    ){
        composable(Destination.Login.route) {
            Login(
                navigateDash = {navController.navigate(Destination.Dashboard.route){
                    popUpTo(Destination.Login.route)
                } },
                navigateForgotPassword = {
                    navController.navigate(Destination.ForgotPassword.route) {
                        restoreState = true
                    }
                },
                navigateCreateAccount = {navController.navigate(Destination.CreateAccount.route)}
            )
        }
        composable(Destination.ForgotPassword.route) {
            ForgotPassword(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.CreateAccount.route) {
            CreateAccount(
                navigateBack =  {navController.popBackStack()},
                navigateDash = {navController.navigate(Destination.Dashboard.route){
                    popUpTo(Destination.Login.route)
                } },
                navigateTOS = {navController.navigate(Destination.TermsOfService.route)},
                navigatePP = {navController.navigate(Destination.PrivacyPolicy.route)}
            )
        }
        composable(Destination.Dashboard.route) {
            Dashboard(
                navigateMenu = {
                    navController.navigate(Destination.Menu.route) {
                        restoreState = true
                    }
                },
                navigateProductDetail = {},
                navigateCreateListing = {navController.navigate(Destination.CreateListing.route)}
            )
        }
        composable(Destination.Menu.route) {
            Menu(
                navigateAccountInfo = {navController.navigate(Destination.AccountInformation.route)},
                navigateBack = {navController.popBackStack()},
                navigateChangePassword = {navController.navigate(Destination.ChangePassword.route)},
                navigateContactUs = {},
                navigateFAQ = {navController.navigate(Destination.FAQ.route)},
                navigateMyListings = {},
                navigateMyPurchases = {},
                navigateNotifications = {},
                navigateLogin = {
                    navController.navigate(Destination.Login.route) {
                        popUpTo(Destination.Login.route) {inclusive = true}
                    }
                }
            )
        }
        composable(Destination.ChangePassword.route) {
            ChangePassword(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.AccountInformation.route) {
            AccountInformation(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.FAQ.route) {
            Faq(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.CreateListing.route) {
            CreateListing(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.PrivacyPolicy.route) {
            PrivacyPolicy(
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(Destination.TermsOfService.route) {
            TermsOfService(
                navigateBack = {navController.popBackStack()}
            )
        }
    }
}