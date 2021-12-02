package com.launchpad.mktfy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MKTFYApp()
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MKTFYApp() {
    MKTFY_AndroidTheme() {
        Surface(color = MaterialTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    Login(
                        navigateDash = {navController.navigate("dashboard"){
                            popUpTo("login")
                        } },
                        navigateForgotPassword = {
                            navController.navigate("forgotPassword") {
                                restoreState = true
                            }
                         },
                        navigateCreateAccount = {navController.navigate("createAccount")}
                    )
                }
                composable("forgotPassword") {
                    ForgotPassword(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("createAccount") {
                    CreateAccount(
                        navigateBack =  {navController.popBackStack()},
                        navigateDash = {navController.navigate("dashboard"){
                            popUpTo("login")
                        } },
                        navigateTOS = {navController.navigate("termsOfService")},
                        navigatePP = {navController.navigate("privacyPolicy")}
                    )
                }
                composable("dashboard") {
                    Dashboard(
                        navigateMenu = {
                            navController.navigate("menu") {
                                restoreState = true
                            }
                        },
                        navigateProductDetail = {},
                        navigateCreateListing = {navController.navigate("createListing")}
                    )
                }
                composable("menu") {
                    Menu(
                        navigateAccountInfo = {navController.navigate("accountInformation")},
                        navigateBack = {navController.popBackStack()},
                        navigateChangePassword = {navController.navigate("changePassword")},
                        navigateContactUs = {},
                        navigateFAQ = {navController.navigate("faq")},
                        navigateMyListings = {},
                        navigateMyPurchases = {},
                        navigateNotifications = {},
                        navigateLogin = {
                            navController.navigate("login") {
                                popUpTo("login") {inclusive = true}
                            }
                        }
                    )
                }
                composable("changePassword") {
                    ChangePassword(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("accountInformation") {
                    AccountInformation(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("faq") {
                    Faq(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("createListing") {
                    CreateListing(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("createListing") {
                    CreateListing(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("privacyPolicy") {
                    PrivacyPolicy(
                        navigateBack = {navController.popBackStack()}
                    )
                }
                composable("termsOfService") {
                    TermsOfService(
                        navigateBack = {navController.popBackStack()}
                    )
                }
            }

        }
    }
}