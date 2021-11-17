package com.launchpad.mktfy_android.ui.screens.accountInformation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AccountInformation(
    navigateBack: () -> Unit
) {
    AccountInformationState(
        navigateBack = navigateBack
    )
}


@Composable
private fun AccountInformationState(
    viewModel: AccountInformationViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    AccountInformationContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                AccountInformationAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun AccountInformationContent(
    viewState: AccountInformationViewState = AccountInformationViewState(),
    actioner: (AccountInformationAction) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Text("Welcome to Account Info")
        Button(onClick = {actioner(AccountInformationAction.NavigateBack)}) {
            Text("Back")
        }
    }
}


@Preview
@Composable
private fun AccountInformationPreview() {
    AccountInformationContent(viewState = AccountInformationViewState())

}