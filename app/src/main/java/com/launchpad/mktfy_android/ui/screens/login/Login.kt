package com.launchpad.mktfy_android.ui.screens.login

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.models.LoadState
import com.launchpad.mktfy_android.ui.theme.*

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
        .background(color = Color.White)
    ) {

        val backgroundAlpha: Float by animateFloatAsState(
            targetValue = if (viewState.showSplashScreen) 1f else 0f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 2000)
        )

        val logoHeightFraction: Float by animateFloatAsState(
            targetValue = if (viewState.showSplashScreen) 1f else 0.4f,
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
        val borderColor = if (viewState.loginLoadState == LoadState.ERROR) ErrorColor else GrayBorderColor
        val topPasswordPadding = if (viewState.loginLoadState == LoadState.ERROR) 5.dp else 24.dp

        val focusManager = LocalFocusManager.current

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
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
                text = stringResource(R.string.email),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = BlackTitle
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
                value = viewState.email,
                onValueChange = {newEmail -> actioner(LoginAction.UpdateEmail(newEmail))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.insert_your_email),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 15.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Gray,
                    unfocusedBorderColor = borderColor
                ),
                // TODO: For debugging purposes, remove later
                trailingIcon = {
                    Icon(
                        Icons.Filled.Adb,
                        contentDescription = null,
                        tint = GrayIconColor,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    if (viewState.loginLoadState == LoadState.NONE)
                                        actioner(LoginAction.UpdateLoginState(LoadState.ERROR))
                                    else
                                        actioner(LoginAction.UpdateLoginState(LoadState.NONE))
                                }
                            )
                    )
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            // Email error
            if (viewState.loginLoadState == LoadState.ERROR) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    text = stringResource(R.string.your_email_is_incorrect),
                    color = ErrorColor,
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }

            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp, top = topPasswordPadding),
                text = stringResource(R.string.password),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = BlackTitle
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
                value = viewState.password,
                onValueChange = {newPassword -> actioner(LoginAction.UpdatePassword(newPassword))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.insert_your_password),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                visualTransformation = visualTransformation,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Gray,
                    unfocusedBorderColor = GrayBorderColor
                ),
                trailingIcon = {
                    Icon(
                        visibilityIcon,
                        contentDescription = null,
                        tint = GrayIconColor,
                        modifier = Modifier
                            .clickable(
                                onClick = {actioner(LoginAction.ShowPassword)}
                            )
                    )
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 15.dp)
                .padding(bottom = 108.dp)
                .clickable { actioner(LoginAction.NavigateForgotPassword) },
                text = stringResource(R.string.i_forgot_my_password),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = YellowOrange,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp
                )
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(bottom = 40.dp)
                .height(64.dp),
                onClick = { actioner(LoginAction.Login) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = YellowOrange,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(37.dp)
            ) {
                Text(
                    text = stringResource(R.string.login),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                )

            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(bottom = 15.dp)
                .height(44.dp),
                onClick = { actioner(LoginAction.NavigateCreateAccount) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = DarkerPurple
                ),
                shape = RoundedCornerShape(36.dp)
            ) {
                Text(
                    text = stringResource(R.string.new_in_the_app_create_an_account),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                )
            }
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