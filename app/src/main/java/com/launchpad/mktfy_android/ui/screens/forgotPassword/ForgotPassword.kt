package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.MarkEmailRead
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.models.LoadState
import com.launchpad.mktfy_android.ui.screens.login.LoginAction
import com.launchpad.mktfy_android.ui.theme.*

@Composable
fun ForgotPassword(
    navigateLogin: () -> Unit,
    navigateResetPassword: () -> Unit
) {
    ForgotPasswordState(
        navigateLogin = navigateLogin,
        navigateResetPassword = navigateResetPassword
    )
}

@Composable
fun ForgotPasswordState(
    ForgotPasswordViewModel: ForgotPasswordViewModel = viewModel(),
    navigateLogin: () -> Unit,
    navigateResetPassword: () -> Unit
) {
    val viewState by ForgotPasswordViewModel.state.collectAsState()
    ForgotPasswordContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                ForgotPasswordAction.NavigateLogin -> navigateLogin()
                ForgotPasswordAction.NavigateResetPassword -> navigateResetPassword()
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
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(71.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBackIos,
                contentDescription = "Back",
                tint = LightPurple,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 15.dp, start = 23.dp)
                    .clickable { actioner(ForgotPasswordAction.NavigateLogin) },
            )
            Text(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
                text = "Forgot Password",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = LightPurple
                )
            )
        }

        val borderColor = if (viewState.emailState == LoadState.ERROR) ErrorColor else GrayBorderColor
        val emailFieldBottomPadding = if (viewState.emailState == LoadState.ERROR) 0.dp else 122.dp
        val resendTextBottomPadding = if (viewState.verifyState == LoadState.ERROR) 30.dp else 49.dp

        val focusManager = LocalFocusManager.current

        // Main Content
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .background(LightGrayBackground)
            .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier
                .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Mail Icon
                Icon(
                    Icons.Filled.MarkEmailRead,
                    contentDescription = null,
                    tint = LightPurple,
                    modifier = Modifier
                        .padding(bottom = 32.dp, top = 62.dp)
                        .background(Color.White, shape = RoundedCornerShape(22.dp))
                        .size(132.dp)
                        .padding(
                            top = 27.12.dp,
                            bottom = 27.12.dp,
                            start = 29.06.dp,
                            end = 30.37.dp
                        )
                )

                // Shows if email needs to be entered
                if (viewState.showEmailField) {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .padding(horizontal = 20.dp),
                        text = "Please enter your email so that we can send you a verification code to reset your password.",
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
                        text = "Email",
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
                            .padding(horizontal = 20.dp),
                        value = viewState.email,
                        onValueChange = { newEmail -> actioner(ForgotPasswordAction.UpdateEmail(newEmail)) },
                        placeholder = {
                            Text(
                                text = "Insert your email",
                                style = TextStyle.Default.copy(
                                    fontFamily = openSansFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        singleLine = true,
                        textStyle = TextStyle.Default.copy(
                            fontFamily = openSansFamily
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Black,
                            placeholderColor = Gray,
                            unfocusedBorderColor = borderColor
                        ),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                    )


                    // Email error
                    if (viewState.emailState == LoadState.ERROR && viewState.showEmailField) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 103.dp),
                            text = "Your email is incorrect",
                            color = ErrorColor,
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }

                // If Verification code needs to be entered
                } else {

                    // Get the email + censor it
                    val censoredEmail = censorEmail(viewState.email)
                    Text(
                        buildAnnotatedString {
                            append("A code has been sent to your email")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(censoredEmail)
                            }
                            append(". Please enter the verification code.")
                        },
                        modifier = Modifier
                        .padding(bottom = 74.dp)
                        .padding(horizontal = 20.dp),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = BlackText
                        )
                    )


                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 10.dp),
                        text = "Verification Code",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = viewState.verificationCode,
                        onValueChange = { newCode -> actioner(ForgotPasswordAction.UpdateVerificationCode(newCode)) },
                        placeholder = {
                            Text(
                                text = "00 - 00 - 00",
                                style = TextStyle.Default.copy(
                                    fontFamily = openSansFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        singleLine = true,
                        textStyle = TextStyle.Default.copy(
                            fontFamily = openSansFamily
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Black,
                            placeholderColor = Gray,
                            unfocusedBorderColor = borderColor
                        ),
                        visualTransformation = VerificationCodeVisualTransformation(),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // Verify error
                    if (viewState.verifyState == LoadState.ERROR && !viewState.showEmailField) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 4.dp),
                            text = "Your Verification Code is incorrect",
                            color = ErrorColor,
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }

                    Text(modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 20.dp, bottom = resendTextBottomPadding)
                        .clickable { /*TODO: Send email*/ },
                        text = "I didn’t receive the code, send it again",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            color = YellowOrange,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 14.sp
                        ))
                }

                // Button
                val buttonText: String = if (viewState.showEmailField) "Send" else "Verify"
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 155.dp)
                    .height(51.dp),
                    shape = RoundedCornerShape(37.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DarkerPurple,
                        contentColor = Color.White
                    ),
                    onClick = {actioner(ForgotPasswordAction.ShowEmailField)}
                ) {
                    Text(
                        text = buttonText,
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

fun censorEmail(email: String): String {
    val matchedIndex: Int = email.indexOf(char = '@')
    if (matchedIndex >= 0) {
        return " " + email.replaceRange(matchedIndex+2, email.length, "*****")
    } else {
        return ""
    }
}


// Helper class for Verification Code format
class VerificationCodeVisualTransformation: VisualTransformation{

    override fun filter(text: AnnotatedString): TransformedText {

        // Making XX - XX - XX string.
        val trimmed = if (text.text.length >= 6) text.text.substring(0..5) else text.text
        var output = ""
        for (i in trimmed.indices) {
            output += trimmed[i]
            if (i % 2 == 1 && i != 5) output += " - "
        }
        val verificationCodeOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 3
                if (offset <= 5) return offset + 6
                return 12
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 6) return offset - 3
                if (offset <= 11) return offset - 6
                return 6
            }
        }
        return TransformedText(AnnotatedString(output), verificationCodeOffsetTranslator)
    }

}

//@Preview (showBackground = true)
@Composable
fun EnterEmailPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState())
    }
}

//@Preview (showBackground = true)
@Composable
fun EnterEmailErrorPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState(emailState = LoadState.ERROR))
    }
}

@Preview (showBackground = true)
@Composable
fun VerifyCodePreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState(showEmailField = false))
    }
}

@Preview (showBackground = true)
@Composable
fun VerifyCodeErrorPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent(ForgotPasswordViewState(showEmailField = false, verifyState = LoadState.ERROR))
    }
}