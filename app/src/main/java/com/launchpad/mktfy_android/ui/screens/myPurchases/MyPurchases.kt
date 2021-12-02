package com.launchpad.mktfy_android.ui.screens.myPurchases

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.components.Header


@Composable
fun MyPurchases(
    navigateBack: () -> Unit,
    navigatePickupInformation: (String) -> Unit
) {
    MyPurchasesState(
        navigateBack = navigateBack,
        navigatePickupInformation = navigatePickupInformation
    )
}


@Composable
private fun MyPurchasesState(
    viewModel: MyPurchasesViewModel = viewModel(),
    navigateBack: () -> Unit,
    navigatePickupInformation: (String) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    MyPurchasesContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                MyPurchasesAction.NavigateBack -> navigateBack()
                is MyPurchasesAction.NavigatePickupInformation -> navigatePickupInformation(action.listingId)
                else -> viewModel.submitAction(action)
            }
        }
    )
    viewModel.getPurchases()
}


@Composable
private fun MyPurchasesContent(
    viewState: MyPurchasesViewState = MyPurchasesViewState(),
    actioner: (MyPurchasesAction) -> Unit = {}
) {
    Column() {
        // HEADER
        Header(
            text = "My Purchases",
            navigateBack = { actioner(MyPurchasesAction.NavigateBack) }
        )

        // Purchases
        Text("Welcome to your purchases")
    }
}


@Preview (showBackground = true)
@Composable
private fun MyPurchasesPreview() {
    MyPurchasesContent(viewState = MyPurchasesViewState())

}