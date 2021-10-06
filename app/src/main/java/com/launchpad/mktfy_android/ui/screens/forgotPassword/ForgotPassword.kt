package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.theme.LightGrayBackground
import com.launchpad.mktfy_android.ui.theme.LightPurple
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme
import com.launchpad.mktfy_android.ui.theme.openSansFamily

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
            .height(95.dp)
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



        // Main Content
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(
                RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .background(LightGrayBackground)
        ) {
            Column(modifier = Modifier
                .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Mail Icon
                Icon(Icons.Filled.MarkEmailRead, contentDescription = null, modifier = Modifier
                    .padding(top = 62.dp)
                    .background(Color.White, shape = RoundedCornerShape(22.dp))
                    .size(132.dp)
                    .padding(top = 27.12.dp, bottom = 27.12.dp, start = 29.06.dp, end = 30.37.dp))
            }
        }

    }

}

@Preview (showBackground = true)
@Composable
fun EnterEmailPreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent()
    }
}

@Preview (showBackground = true)
@Composable
fun VerifyCodePreview() {
    MKTFY_AndroidTheme {
        ForgotPasswordContent()
    }
}