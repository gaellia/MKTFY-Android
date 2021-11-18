package com.launchpad.mktfy_android.ui.screens.createListing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.theme.LightGrayBackground


@Composable
fun CreateListing(
    navigateBack: () -> Unit
) {
    CreateListingState(
        navigateBack = navigateBack
    )
}


@Composable
private fun CreateListingState(
    viewModel: CreateListingViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    CreateListingContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                CreateListingAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun CreateListingContent(
    viewState: CreateListingViewState = CreateListingViewState(),
    actioner: (CreateListingAction) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ) {
        //HEADER
        Header(
            text = "Create Listing",
            navigateBack = { actioner(CreateListingAction.NavigateBack) }
        )

        // CONTENT
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .background(
                color = LightGrayBackground,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .verticalScroll(rememberScrollState())
        ) {
            Text(modifier = Modifier
                .fillMaxSize(),
                text = "Welcome to create a listing",
                textAlign = TextAlign.Center,
                style = TextStyle.Default.copy(color = Color.Black)
            )
        }
    }

}


@Preview
@Composable
private fun CreateListingPreview() {
    CreateListingContent(viewState = CreateListingViewState())

}