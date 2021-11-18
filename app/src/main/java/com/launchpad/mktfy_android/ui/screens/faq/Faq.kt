package com.launchpad.mktfy_android.ui.screens.faq

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Faq(
    navigateBack: () -> Unit
) {
    FaqState(
        navigateBack = navigateBack
    )
}


@Composable
private fun FaqState(
    viewModel: FaqViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    FaqContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                FaqAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun FaqContent(
    viewState: FaqViewState = FaqViewState(),
    actioner: (FaqAction) -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Welcome to Faq")
        Button(onClick = {actioner(FaqAction.NavigateBack)}) {
            Text("Back")
        }
    }
}


@Preview (showBackground = true)
@Composable
private fun FaqPreview() {
    FaqContent(viewState = FaqViewState())

}