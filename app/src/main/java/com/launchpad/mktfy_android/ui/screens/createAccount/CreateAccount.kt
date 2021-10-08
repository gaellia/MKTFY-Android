package com.launchpad.mktfy_android.ui.screens.createAccount

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme

@Composable
fun CreateAccount(
    navigateBack: () -> Unit,
    navigateHome: () -> Unit
) {
    CreateAccountState(
        navigateBack = navigateBack,
        navigateHome = navigateHome
    )
}

@Composable
fun CreateAccountState(
    CreateAccountViewModel: CreateAccountViewModel = viewModel(),
    navigateBack: () -> Unit,
    navigateHome: () -> Unit
) {
    val viewState by CreateAccountViewModel.state.collectAsState()
    CreateAccountContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                CreateAccountAction.NavigateBack -> navigateBack()
                CreateAccountAction.NavigateHome -> navigateHome()
                else -> CreateAccountViewModel.collectAction(action)
            }
        }
    )
}

@Composable
fun CreateAccountContent(
    viewState: CreateAccountViewState = CreateAccountViewState(),
    actioner: (CreateAccountAction) -> Unit = {}
) {

}

@Preview (showBackground = true)
@Composable
fun EnterEmailPreview() {
    MKTFY_AndroidTheme {
        CreateAccountContent()
    }
}

@Preview (showBackground = true)
@Composable
fun CreateAccountPasswordPreview() {
    MKTFY_AndroidTheme {
        CreateAccountContent(CreateAccountViewState(showCreatePasswordScreen = true))
    }
}