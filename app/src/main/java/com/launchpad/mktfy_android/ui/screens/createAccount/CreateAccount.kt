package com.launchpad.mktfy_android.ui.screens.createAccount

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.models.PhoneNumberVisualTransformation
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.theme.*

@ExperimentalAnimationApi
@Composable
fun CreateAccount(
    navigateBack: () -> Unit,
    navigateDash: () -> Unit,
    navigateTOS: () -> Unit,
    navigatePP: () -> Unit
) {
    CreateAccountState(
        navigateBack = navigateBack,
        navigateDash = navigateDash,
        navigateTOS = navigateTOS,
        navigatePP = navigatePP
    )
}

@ExperimentalAnimationApi
@Composable
fun CreateAccountState(
    CreateAccountViewModel: CreateAccountViewModel = viewModel(),
    navigateBack: () -> Unit,
    navigateDash: () -> Unit,
    navigateTOS: () -> Unit,
    navigatePP: () -> Unit
) {
    val viewState by CreateAccountViewModel.state.collectAsState()
    CreateAccountContent(
        viewState = viewState,
        actioner = { action ->
            when(action) {
                CreateAccountAction.NavigateBack -> navigateBack()
                CreateAccountAction.NavigateDash -> navigateDash()
                CreateAccountAction.NavigateTOS -> navigateTOS()
                CreateAccountAction.NavigatePP -> navigatePP()
                else -> CreateAccountViewModel.collectAction(action)
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun CreateAccountContent(
    viewState: CreateAccountViewState = CreateAccountViewState(),
    actioner: (CreateAccountAction) -> Unit = {}
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
    ) {
        //Header
        Header(
            text = stringResource(R.string.create_account),
            navigateBack = {
                if (!viewState.showCreatePasswordScreen) actioner(CreateAccountAction.NavigateBack) else
                    actioner(CreateAccountAction.HideCreatePasswordScreen)
            }
        )

        val focusManager = LocalFocusManager.current
        // First Page
        AnimatedVisibility(visible = !viewState.showCreatePasswordScreen,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 250))
        ) {
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
                            CreateAccountAction.UpdateFirstName(
                                newFirstName
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your name",
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
                        placeholderColor = Gray,
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
                            CreateAccountAction.UpdateLastName(
                                newLastName
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your last name",
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
                        placeholderColor = Gray,
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
                    )
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .padding(horizontal = 20.dp),
                    value = viewState.email,
                    onValueChange = { newEmail ->
                        actioner(
                            CreateAccountAction.UpdateEmail(
                                newEmail
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your email",
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
                        placeholderColor = Gray,
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
                            CreateAccountAction.UpdatePhone(
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
                        placeholderColor = Gray,
                        unfocusedBorderColor = GrayBorderColor
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    visualTransformation = PhoneNumberVisualTransformation()
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp),
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
                            CreateAccountAction.UpdateCountry(
                                newCountry
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your country",
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
                        placeholderColor = Gray,
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
                            CreateAccountAction.UpdateProvince(
                                newProvince
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your province",
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
                        placeholderColor = Gray,
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
                            CreateAccountAction.UpdateCity(
                                newCity
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your city",
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
                        placeholderColor = Gray,
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
                        .padding(top = 10.dp)
                        .padding(horizontal = 20.dp),
                    value = viewState.address,
                    onValueChange = { newAddress ->
                        actioner(
                            CreateAccountAction.UpdateAddress(
                                newAddress
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Insert your address",
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
                        placeholderColor = Gray,
                        unfocusedBorderColor = GrayBorderColor
                    ),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )

                //Check if all fields are filled
                val enableNextButton: Boolean = viewState.firstName.isNotEmpty() &&
                        viewState.lastName.isNotEmpty() &&
                        viewState.email.isNotEmpty() &&
                        viewState.phone.isNotEmpty() &&
                        viewState.country.isNotEmpty() &&
                        viewState.province.isNotEmpty() &&
                        viewState.city.isNotEmpty() &&
                        viewState.address.isNotEmpty()

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 22.dp, bottom = 14.dp)
                        .height(51.dp),
                    onClick = { actioner(CreateAccountAction.ShowCreatePasswordScreen) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DarkerPurple,
                        contentColor = Color.White,
                        disabledBackgroundColor = DisabledButtonColor,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(37.dp),
                    enabled = enableNextButton
                ) {
                    Text(
                        text = "Next",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }

        // Second Page
        AnimatedVisibility(visible = viewState.showCreatePasswordScreen,
            enter = fadeIn(animationSpec = tween(durationMillis = 750)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .background(
                    color = LightGrayBackground,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .verticalScroll(rememberScrollState()),
            ) {
                //Hide password screen back handler
                BackHandler(
                    onBack = { actioner(CreateAccountAction.HideCreatePasswordScreen) }
                )

                val passwordVisibilityIcon = if (viewState.showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val passwordVisualTransformation = if (viewState.showPassword) VisualTransformation.None else PasswordVisualTransformation()

                val confirmPasswordVisibilityIcon = if (viewState.showConfirmPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val confirmPasswordVisualTransformation = if (viewState.showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation()
                val confirmPasswordColor = if (viewState.createAccountErrorState) ErrorColor else BlackTitle
                val visibilityIconColor = if (viewState.createAccountErrorState) RedIconColor else GrayIconColor
                val borderColor = if (viewState.createAccountErrorState) ErrorColor else GrayBorderColor
                val bottomConfirmPasswordPadding = if (viewState.createAccountErrorState) 0.dp else 26.dp

                val lengthCheckColor = if (viewState.atLeastSixChars) LightPurple else UncheckedColor
                val upperCheckColor = if (viewState.hasOneUppercase) LightPurple else UncheckedColor
                val numCheckColor = if (viewState.hasOneNumber) LightPurple else UncheckedColor

                val checkbox = if (viewState.isChecked) R.drawable.check_box_24px else R.drawable.check_box_empty

                Text(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .padding(horizontal = 20.dp),
                    text = "The password must have at least 6 characters and must contain 1 uppercase and 1 number.",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = BlackText
                    )
                )

                Text(
                    buildAnnotatedString {
                        append("Password")
                        withStyle(style = if (viewState.atLeastSixChars && viewState.hasOneUppercase && viewState.hasOneNumber)
                            SpanStyle(color = GreenText) else SpanStyle(color = OrangeText)
                        ) {
                            if (viewState.atLeastSixChars && viewState.hasOneUppercase && viewState.hasOneNumber)
                                append("  Strong") else append("  Weak")
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
                    .padding(top = 10.dp),
                    value = viewState.password,
                    onValueChange = {newPassword -> actioner(CreateAccountAction.UpdatePassword(newPassword))},
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
                        unfocusedBorderColor = GrayBorderColor
                    ),
                    trailingIcon = {
                        Icon(
                            passwordVisibilityIcon,
                            contentDescription = null,
                            tint = GrayIconColor,
                            modifier = Modifier
                                .clickable(
                                    onClick = {actioner(CreateAccountAction.ShowPassword)}
                                )
                        )
                    },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )

                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                    text = "Confirm Password",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    ),
                    color = confirmPasswordColor
                )
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp, bottom = bottomConfirmPasswordPadding),
                    value = viewState.confirmPassword,
                    onValueChange = {newConfirmPassword -> actioner(CreateAccountAction.UpdateConfirmPassword(newConfirmPassword))},
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
                        unfocusedBorderColor = borderColor
                    ),
                    trailingIcon = {
                        Icon(
                            confirmPasswordVisibilityIcon,
                            contentDescription = null,
                            tint = visibilityIconColor,
                            modifier = Modifier
                                .clickable(
                                    onClick = {actioner(CreateAccountAction.ShowConfirmPassword)}
                                )
                        )
                    },
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )

                if (viewState.createAccountErrorState) {
                    Text(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 7.dp),
                        text = "Your password is incorrect",
                        color = ErrorColor,
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }

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
                        text = "At least 6 characters",
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
                        text = "1 Uppercase",
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
                            .padding(start = 12.dp),
                        text = "1 Number",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = BlackText
                        )
                    )
                }
                
                Spacer(modifier = Modifier.weight(1f))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = checkbox),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { actioner(CreateAccountAction.UpdateTOSCheck) }
                    )
                    val annotatedText = buildAnnotatedString {
                        append("By checking this box, you agree to our ")

                        pushStringAnnotation(tag = "TOS", annotation = "TOS")
                        withStyle(style = SpanStyle(
                            color = LightPurple,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline)
                        ) {
                            append("Terms of service")
                        }
                        pop()

                        append(" and our ")

                        pushStringAnnotation(tag = "PP", annotation = "PP")
                        withStyle(style = SpanStyle(
                            color = LightPurple,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline)
                        ) {
                            append("Privacy Policy")
                        }
                        pop()
                    }

                    ClickableText(
                        text = annotatedText,
                        onClick = { offset ->
                            annotatedText.getStringAnnotations(
                                start = offset,
                                end = offset
                            ).takeIf { it.isNotEmpty() }?.first()?.let { annotation ->
                                when(annotation.tag) {
                                    "TOS" -> { actioner(CreateAccountAction.NavigateTOS) }
                                    "PP" -> { actioner(CreateAccountAction.NavigatePP) }
                                }
                            }
                        },
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = BlackText
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 32.dp, bottom = 13.dp)
                        .height(51.dp),
                    onClick = { actioner(CreateAccountAction.NavigateDash) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = DarkerPurple,
                        contentColor = Color.White,
                        disabledBackgroundColor = DisabledButtonColor,
                        disabledContentColor = Color.White
                    ),
                    shape = RoundedCornerShape(37.dp),
                    enabled = viewState.atLeastSixChars && viewState.hasOneUppercase
                            && viewState.hasOneNumber && viewState.isChecked &&
                            viewState.password == viewState.confirmPassword
                ) {
                    Text(
                        text = "Create My Account",
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
}

@ExperimentalAnimationApi
@Preview (showBackground = true, heightDp = 1500)
@Composable
fun EnterEmailPreview() {
    MKTFY_AndroidTheme {
        CreateAccountContent()
    }
}

@ExperimentalAnimationApi
@Preview (showBackground = true)
@Composable
fun CreateAccountPasswordPreview() {
    MKTFY_AndroidTheme {
        CreateAccountContent(CreateAccountViewState(showCreatePasswordScreen = true))
    }
}

@ExperimentalAnimationApi
@Preview (showBackground = true)
@Composable
fun CreateAccountPasswordErrorPreview() {
    MKTFY_AndroidTheme {
        CreateAccountContent(CreateAccountViewState(showCreatePasswordScreen = true, createAccountErrorState = true))
    }
}