package com.launchpad.mktfy_android.ui.screens.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Dashboard(
    navigateCreateListing: () -> Unit,
    navigateProductDetail: () -> Unit,
    navigateMenu: () -> Unit
) {
    DashboardState(
        navigateCreateListing = navigateCreateListing,
        navigateProductDetail = navigateProductDetail,
        navigateMenu = navigateMenu
    )
}


@Composable
private fun DashboardState(
    viewModel: DashboardViewModel = viewModel(),
    navigateCreateListing: () -> Unit,
    navigateProductDetail: () -> Unit,
    navigateMenu: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    DashboardContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                DashboardAction.NavigateCreateListing -> navigateCreateListing()
                DashboardAction.NavigateMenu -> navigateMenu()
                DashboardAction.NavigateProductDetail -> navigateProductDetail()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun DashboardContent(
    viewState: DashboardViewState = DashboardViewState(),
    actioner: (DashboardAction) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text("Welcome to the dashboard")
    }


}


@Preview
@Composable
private fun DashboardPreview() {
    DashboardContent(viewState = DashboardViewState())

}