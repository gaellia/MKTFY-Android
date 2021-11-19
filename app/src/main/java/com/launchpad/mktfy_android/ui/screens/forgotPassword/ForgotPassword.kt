package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.theme.*

@Composable
fun ForgotPassword(
    navigateBack: () -> Unit,
) {
    ForgotPasswordState(
        navigateBack = navigateBack,
    )
}

@Composable
fun ForgotPasswordState(
    ForgotPasswordViewModel: ForgotPasswordViewModel = viewModel(),
    navigateBack: () -> Unit,
) {
    val viewState by ForgotPasswordViewModel.state.collectAsState()
    ForgotPasswordContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                ForgotPasswordAction.NavigateBack -> navigateBack()
                else -> ForgotPasswordViewModel.collectAction(action)
            }
        }
    )
}

@Composable
fun ForgotPasswordContent(
    viewState: ForgotPasswordViewState = ForgotPasswordViewState(),
    actioner: (ForgotPasswordAction) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {

        // Header
        Header(
            text = stringResource(id = R.string.forgot_password),
            navigateBack = { actioner(ForgotPasswordAction.NavigateBack) }
        )

        val borderColor = if (viewState.showEmailError) ErrorColor else GrayBorderColor
        val emailFieldBottomPadding = if (viewState.showEmailError) 0.dp else 122.dp

        val focusManager = LocalFocusManager.current

        // Main Content
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .background(
                color = LightGrayBackground,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Mail Icon
                Icon(
                    painter = painterResource(id = R.drawable.icon_email),
                    tint = Color.Unspecified,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 32.dp, top = 62.dp)
                        .size(132.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 32.dp)
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.enter_email_send_code),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = BlackText
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.email),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = BlackTitle
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = emailFieldBottomPadding)
                        .padding(horizontal = 20.dp)
                        .background(Color.White),
                    value = viewState.email,
                    onValueChange = { newEmail -> actioner(ForgotPasswordAction.UpdateEmail(newEmail)) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.insert_your_email),
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
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
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )


                // Email error
                if (viewState.showEmailError) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 103.dp),
                        text = stringResource(R.string.your_email_is_incorrect),
                        color = ErrorColor,
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }

                // Button
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(51.dp),
                    shape = RoundedCornerShape(37.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DarkerPurple,
                        contentColor = Color.White
                    ),
                    onClick = { /*TODO: Send email loading screen*/ }
                ) {
                    Text(
                        text = stringResource(R.string.send),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun EnterEmailPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState())
    }
}

@Preview (showBackground = true)
@Composable
fun EnterEmailErrorPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState(showEmailError = true))
    }
}