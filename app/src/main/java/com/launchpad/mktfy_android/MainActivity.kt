package com.launchpad.mktfy_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.launchpad.mktfy_android.ui.screens.createAccount.CreateAccount
import com.launchpad.mktfy_android.ui.screens.dashboard.Dashboard
import com.launchpad.mktfy_android.ui.screens.forgotPassword.ForgotPassword
import com.launchpad.mktfy_android.ui.screens.login.Login
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MKTFYApp()
        }
    }
}

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
                        navigateTOS = {},
                        navigatePP = {}
                    )
                }
                composable("dashboard") {
                    Dashboard(
                        navigateMenu = {},
                        navigateProductDetail = {},
                        navigateCreateListing = {}
                    )
                }
            }

        }
    }
}