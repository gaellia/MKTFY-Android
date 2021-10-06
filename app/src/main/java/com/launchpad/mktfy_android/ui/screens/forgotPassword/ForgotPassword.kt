package com.launchpad.mktfy_android.ui.screens.forgotPassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

@Composable
fun ForgotPassword() {
    ForgotPasswordState()
}

@Composable
fun ForgotPasswordState(
    ForgotPasswordViewModel: ForgotPasswordViewModel = viewModel()
) {
    val viewState by ForgotPasswordViewModel.state.collectAsState()
    ForgotPasswordContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
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