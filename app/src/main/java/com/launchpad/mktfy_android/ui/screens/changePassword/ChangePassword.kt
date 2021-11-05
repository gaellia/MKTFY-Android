package com.launchpad.mktfy_android.ui.screens.changePassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ChangePassword(
    navigateBack: () -> Unit
) {
    ChangePasswordState(
        navigateBack = navigateBack
    )
}


@Composable
private fun ChangePasswordState(
    viewModel: ChangePasswordViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    ChangePasswordContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                ChangePasswordAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun ChangePasswordContent(
    viewState: ChangePasswordViewState = ChangePasswordViewState(),
    actioner: (ChangePasswordAction) -> Unit = {}
) {
    Column() {
        Text("Welcome to Change Password screen")
        Button(onClick = {actioner(ChangePasswordAction.NavigateBack)}) {
            Text("Back")
        }
    }

}


@Preview
@Composable
private fun ChangePasswordPreview() {
    ChangePasswordContent(viewState = ChangePasswordViewState())

}