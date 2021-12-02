package com.launchpad.mktfy_android.ui.screens.pp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.theme.DarkerPurple
import com.launchpad.mktfy_android.ui.theme.LightGrayBackground


@Composable
fun PrivacyPolicy(
    navigateBack: () -> Unit
) {
    PrivacyPolicyState(
        navigateBack = navigateBack
    )
}


@Composable
private fun PrivacyPolicyState(
    viewModel: PrivacyPolicyViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    PrivacyPolicyContent(
        viewState = viewState,
        actioner = { action ->
            when (action) {
                PrivacyPolicyAction.NavigateBack -> navigateBack()
                else -> viewModel.submitAction(action)
            }
        }
    )
}


@Composable
private fun PrivacyPolicyContent(
    viewState: PrivacyPolicyViewState = PrivacyPolicyViewState(),
    actioner: (PrivacyPolicyAction) -> Unit = {}
) {
    Column {
        // HEADER
        Header(
            text = "Privacy Policy",
            navigateBack = {actioner(PrivacyPolicyAction.NavigateBack)}
        )

        // Currently hard-coded, not sure if there will be an endpoint for this
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .background(
                    color = LightGrayBackground,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 43.dp, start = 20.dp, end = 20.dp),
                text = stringResource(id = R.string.privacy_policy),
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 15.sp
                )
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 22.dp)
                    .height(50.dp),
                onClick = { actioner(PrivacyPolicyAction.NavigateBack) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkerPurple,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(37.dp)
            ) {
                Text(
                    text = "Acknowledge",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 43.sp
                    )
                )
            }
        }
    }
}


@Preview (showBackground = true)
@Composable
private fun PrivacyPolicyPreview() {
    PrivacyPolicyContent(viewState = PrivacyPolicyViewState())
}