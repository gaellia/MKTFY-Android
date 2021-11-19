package com.launchpad.mktfy_android.ui.screens.changePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.theme.*


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
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ) {
        //Header
        Header(
            text = stringResource(R.string.change_password),
            navigateBack = { actioner(ChangePasswordAction.NavigateBack) }
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .background(
                color = LightGrayBackground,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .verticalScroll(rememberScrollState())
        ) {
            val currentPasswordVisibilityIcon = if (viewState.showCurrentPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val currentPasswordVisualTransformation = if (viewState.showCurrentPassword) VisualTransformation.None else PasswordVisualTransformation()
            val currentPasswordColor = if (viewState.currentPasswordErrorState) ErrorColor else BlackTitle
            val visibilityIconColor = if (viewState.currentPasswordErrorState) RedIconColor else GrayIconColor
            val borderColor = if (viewState.currentPasswordErrorState) ErrorColor else GrayBorderColor
            val bottomCurrentPasswordPadding = if (viewState.currentPasswordErrorState) 0.dp else 32.dp

            val passwordVisibilityIcon = if (viewState.showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val passwordVisualTransformation = if (viewState.showPassword) VisualTransformation.None else PasswordVisualTransformation()

            val confirmPasswordVisibilityIcon = if (viewState.showConfirmPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
            val confirmPasswordVisualTransformation = if (viewState.showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation()

            val lengthCheckColor = if (viewState.atLeastSixChars) LightPurple else UncheckedColor
            val upperCheckColor = if (viewState.hasOneUppercase) LightPurple else UncheckedColor
            val numCheckColor = if (viewState.hasOneNumber) LightPurple else UncheckedColor

            val focusManager = LocalFocusManager.current

            // Current Password
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 32.dp),
                text = stringResource(R.string.current_password),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = currentPasswordColor
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp, bottom = bottomCurrentPasswordPadding)
                .background(Color.White),
                value = viewState.currentPassword,
                onValueChange = {newCurrentPassword -> actioner(ChangePasswordAction.UpdateCurrentPasswordField(newCurrentPassword))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.insert_your_password),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                visualTransformation = currentPasswordVisualTransformation,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Gray,
                    unfocusedBorderColor = borderColor,
                    backgroundColor = Color.White
                ),
                trailingIcon = {
                    Icon(
                        currentPasswordVisibilityIcon,
                        contentDescription = null,
                        tint = visibilityIconColor,
                        modifier = Modifier
                            .clickable(
                                onClick = {actioner(ChangePasswordAction.ShowCurrentPassword)}
                            )
                    )
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )
            if (viewState.currentPasswordErrorState) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 13.dp),
                    text = stringResource(R.string.your_password_is_incorrect),
                    color = ErrorColor,
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }

            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                text = stringResource(R.string.password_requirements),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = BlackText
                )
            )

            // New Password
            Text(
                buildAnnotatedString {
                    append(stringResource(R.string.password))
                    withStyle(style = if (viewState.atLeastSixChars && viewState.hasOneUppercase && viewState.hasOneNumber)
                        SpanStyle(color = GreenText) else SpanStyle(color = OrangeText)
                    ) {
                        if (viewState.atLeastSixChars && viewState.hasOneUppercase && viewState.hasOneNumber)
                            append(stringResource(R.string._strong)) else append(stringResource(R.string._weak))
                    }
                },
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackTitle
                ),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 20.dp)
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp)
                .background(Color.White),
                value = viewState.password,
                onValueChange = {newPassword -> actioner(ChangePasswordAction.UpdatePasswordField(newPassword))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.insert_your_password),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                visualTransformation = passwordVisualTransformation,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Gray,
                    unfocusedBorderColor = GrayBorderColor,
                    backgroundColor = Color.White
                ),
                trailingIcon = {
                    Icon(
                        passwordVisibilityIcon,
                        contentDescription = null,
                        tint = GrayIconColor,
                        modifier = Modifier
                            .clickable(
                                onClick = {actioner(ChangePasswordAction.ShowPassword)}
                            )
                    )
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            // Confirm New Password
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp),
                text = stringResource(R.string.confirm_password),
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp, bottom = 26.dp)
                .background(Color.White),
                value = viewState.confirmPassword,
                onValueChange = {newConfirmPassword -> actioner(ChangePasswordAction.UpdateConfirmPasswordField(newConfirmPassword))},
                placeholder = {
                    Text(
                        text = stringResource(R.string.insert_your_password),
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    )
                },
                singleLine = true,
                visualTransformation = confirmPasswordVisualTransformation,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    placeholderColor = Gray,
                    unfocusedBorderColor = borderColor,
                    backgroundColor = Color.White
                ),
                trailingIcon = {
                    Icon(
                        confirmPasswordVisibilityIcon,
                        contentDescription = null,
                        tint = visibilityIconColor,
                        modifier = Modifier
                            .clickable(
                                onClick = {actioner(ChangePasswordAction.ShowConfirmPassword)}
                            )
                    )
                },
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_check_circle),
                    tint = lengthCheckColor,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = stringResource(R.string.at_least_6_characters),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = BlackText
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_check_circle),
                    tint = upperCheckColor,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = stringResource(R.string.one_uppercase),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = BlackText
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_check_circle),
                    tint = numCheckColor,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp, bottom = 20.dp),
                    text = stringResource(R.string.one_number),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = BlackText
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp)
                    .height(51.dp),
                onClick = { actioner(ChangePasswordAction.UpdatePassword) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkerPurple,
                    contentColor = Color.White,
                    disabledBackgroundColor = DisabledButtonColor,
                    disabledContentColor = Color.White
                ),
                shape = RoundedCornerShape(37.dp),
                enabled = viewState.atLeastSixChars && viewState.hasOneUppercase
                        && viewState.hasOneNumber && viewState.password == viewState.confirmPassword
            ) {
                Text(
                    text = stringResource(R.string.update_password),
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChangePasswordPreview() {
    ChangePasswordContent(viewState = ChangePasswordViewState())

}