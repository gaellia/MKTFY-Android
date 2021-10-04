package com.launchpad.mktfy_android.ui.screens.login

import android.graphics.drawable.Icon
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.theme.LightPurple
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

@Composable
fun Login(
    navigateHome: () -> Unit,
    navigateForgotPassword: () -> Unit,
    navigateCreateAccount: () -> Unit
) {
    LoginState(navigateHome = navigateHome,
        navigateForgotPassword = navigateForgotPassword,
        navigateCreateAccount = navigateCreateAccount
    )
}

@Composable
fun LoginState(
    loginViewModel: LoginViewModel = viewModel(),
    navigateHome: () -> Unit,
    navigateForgotPassword: () -> Unit,
    navigateCreateAccount: () -> Unit
) {
    val viewState by loginViewModel.state.collectAsState()
    LoginContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                LoginAction.NavigateCreateAccount -> navigateCreateAccount()
                LoginAction.NavigateForgotPassword -> navigateForgotPassword()
                LoginAction.NavigateHome -> navigateHome()
                else -> loginViewModel.collectAction(action)
            }
        }
    )
    loginViewModel.animateLogin()
}

@Composable
fun LoginContent(
    viewState: LoginViewState = LoginViewState(),
    actioner: (LoginAction) -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {

        val backgroundAlpha: Float by animateFloatAsState(
            targetValue = if (viewState.showSplashScreen) 1f else 0f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val logoHeightFraction: Float by animateFloatAsState(
            targetValue = if (viewState.showSplashScreen) 1f else 0.5f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val logoColor by animateColorAsState(
            targetValue = if (viewState.showSplashScreen) Color.White else LightPurple,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val footerYOffset by animateDpAsState(
            targetValue = if (viewState.showSplashScreen) 250.dp else 0.dp,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val inputAlpha: Float by animateFloatAsState(
            targetValue = if (!viewState.showSplashScreen) 1f else 0f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val visibilityIcon = if (viewState.showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
        val visualTransformation = if (viewState.showPassword) VisualTransformation.None else PasswordVisualTransformation()


        //Splash
        Image(modifier = Modifier
            .alpha(backgroundAlpha)
            .fillMaxSize(),
            painter = painterResource(id = R.drawable.img_clouds_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        //Logo
        Image(modifier = Modifier
            .fillMaxHeight(logoHeightFraction)
            .align(Alignment.TopCenter),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            colorFilter = ColorFilter.tint(logoColor)
        )

        //Footer Image
        Image(modifier = Modifier
            .fillMaxSize()
            .offset(y = footerYOffset),
            painter = painterResource(id = R.drawable.img_background_clouds_footer),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        //Login
        Column(modifier = Modifier
            .alpha(inputAlpha)
            .align(Alignment.BottomCenter)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = viewState.password,
                onValueChange = {newPassword -> actioner(LoginAction.UpdatePassword(newPassword))},
                placeholder = {
                    Text(text = "Insert your password")
                },
                singleLine = true,
                visualTransformation = visualTransformation,
                trailingIcon = {
                    Icon(
                        visibilityIcon,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(
                                onClick = {actioner(LoginAction.ShowPassword(viewState.showPassword))}
                            )
                    )
                }
            )
        }


    }

}

@Preview
@Composable
fun SplashPreview() {
    MKTFY_AndroidTheme {
        LoginContent()
    }
}

@Preview (showBackground = true)
@Composable
fun LoginPreview() {
    MKTFY_AndroidTheme {
        LoginContent(LoginViewState(showSplashScreen = false))
    }
}