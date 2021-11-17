package com.launchpad.mktfy_android.ui.screens.accountInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.models.PhoneNumberVisualTransformation
import com.launchpad.mktfy_android.ui.theme.*

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
        // HEADER
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(71.dp)
            .background(color = Color.White),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = "Back",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(bottom = 15.dp, start = 15.dp)
                    .size(24.dp)
                    .clickable { actioner(AccountInformationAction.NavigateBack) },
            )
            Text(modifier = Modifier
                .padding(bottom = 16.dp),
                text = "Account Information",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = LightPurple
                )
            )
            Text(modifier = Modifier
                .padding(bottom = 16.dp, end = 20.dp)
                .clickable(enabled = false) {
                    actioner(AccountInformationAction.NavigateBack)
                },
                text = "Save",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    //TODO: If there is a change, color = LightPurple
                    color = DisabledTextColor
                )
            )
        }
        // TODO: Replace placeholders with current account info
        // CONTENT
        val focusManager = LocalFocusManager.current
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
                    .align(Alignment.Start)
                    .padding(top = 32.dp, start = 20.dp, end = 20.dp),
                text = "First Name",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.firstName,
                onValueChange = { newFirstName ->
                    actioner(
                        AccountInformationAction.UpdateFirstName(
                            newFirstName
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your name",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Last Name",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.lastName,
                onValueChange = { newLastName ->
                    actioner(
                        AccountInformationAction.UpdateLastName(
                            newLastName
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your last name",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Email",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                ),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.email,
                onValueChange = { /*Read only*/},
                placeholder = {
                    Text(
                        text = "Your email",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                readOnly = true,
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Phone",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.phone,
                onValueChange = { newPhone ->
                    actioner(
                        AccountInformationAction.UpdatePhone(
                            newPhone
                        )
                    )
                },
                placeholder = {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Black)) {
                                append("+1 ")
                            }
                            append("(000) 000 - 0000")
                        },
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                visualTransformation = PhoneNumberVisualTransformation()
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp, start = 20.dp, end = 20.dp),
                text = "Address Information",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Country",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.country,
                onValueChange = { newCountry ->
                    actioner(
                        AccountInformationAction.UpdateCountry(
                            newCountry
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your country",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Province",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.province,
                onValueChange = { newProvince ->
                    actioner(
                        AccountInformationAction.UpdateProvince(
                            newProvince
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your province",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "City",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.city,
                onValueChange = { newCity ->
                    actioner(
                        AccountInformationAction.UpdateCity(
                            newCity
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your city",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                text = "Address",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 27.dp)
                    .padding(horizontal = 20.dp),
                value = viewState.address,
                onValueChange = { newAddress ->
                    actioner(
                        AccountInformationAction.UpdateAddress(
                            newAddress
                        )
                    )
                },
                placeholder = {
                    Text(
                        text = "Your address",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
            //TODO: Check if any fields is different
        }
    }
}


@Preview
@Composable
private fun AccountInformationPreview() {
    AccountInformationContent(viewState = AccountInformationViewState())

}