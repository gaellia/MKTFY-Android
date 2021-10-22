package com.launchpad.mktfy_android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.screens.forgotPassword.ForgotPasswordAction
import com.launchpad.mktfy_android.ui.theme.LightPurple
import com.launchpad.mktfy_android.ui.theme.MKTFY_AndroidTheme
import com.launchpad.mktfy_android.ui.theme.openSansFamily

@Composable
fun Header(
    text: String = "",
    navigateBack: () -> Unit = {}
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(71.dp)
        .background(color = Color.White)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_back),
            contentDescription = "Back",
            tint = Color.Unspecified,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 15.dp, start = 15.dp)
                .size(24.dp)
                .clickable { navigateBack() },
        )
        Text(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 16.dp),
            text = text,
            style = TextStyle.Default.copy(
                fontFamily = openSansFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = LightPurple
            )
        )
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    MKTFY_AndroidTheme() {
        Header(
            text = "A Screen"
        )
    }
}