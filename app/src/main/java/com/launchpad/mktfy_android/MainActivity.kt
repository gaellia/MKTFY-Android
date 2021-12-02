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
import com.launchpad.mktfy_android.navigation.AppNavigation
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
            AppNavigation(navController = navController)
        }
    }
}