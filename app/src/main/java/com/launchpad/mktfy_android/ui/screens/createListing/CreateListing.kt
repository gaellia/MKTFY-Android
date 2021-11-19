package com.launchpad.mktfy_android.ui.screens.createListing

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.components.Header
import com.launchpad.mktfy_android.ui.screens.createAccount.CreateAccountAction
import com.launchpad.mktfy_android.ui.theme.*


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
            )
            .verticalScroll(rememberScrollState())
        ) {
            // If no photos have been selected, show button to add
            if (viewState.photos.isEmpty()) {
                OutlinedButton(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(158.dp),
                    border = BorderStroke(1.dp, DarkerPurple),
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = LightGrayBackground,
                        contentColor = DarkerPurple
                    ),
                    onClick = { },
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(painter = painterResource(id = R.drawable.icon_camera),
                            contentDescription = null,
                            tint = DarkerPurple
                        )
                        Text(modifier = Modifier.padding(top = 5.dp),
                            text = "Choose up to 3 photos",
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        )
                    }
                }
            // otherwise, show gallery of images
            } else {
                Image(modifier = Modifier
                    .fillMaxSize(),
                    painter = rememberImagePainter(
                        data = viewState.photos.first().photoPath,
                        builder = {
                            // TODO: optimize getting the image?
                            size(OriginalSize)
                            scale(Scale.FIT)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
                Text(viewState.photos.first().id.toString())
            }

            // Product Fields
            val focusManager = LocalFocusManager.current
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Product Name",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.productName,
                onValueChange = { newProductName ->
                    actioner(
                        CreateListingAction.UpdateProductName(newProductName)
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Description",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(158.dp)
                    .padding(top = 6.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.description,
                onValueChange = { newDesc ->
                    actioner(
                        CreateListingAction.UpdateDescription(newDesc)
                    )
                },
                maxLines = 5,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            //TODO: Dropdown for Category
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Category",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.category.category,
                onValueChange = { },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            //TODO: Dropdown for Condition
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Condition",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.condition.condition,
                onValueChange = { },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            // TODO: Price Formatting
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Price",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.price,
                onValueChange = { newPrice ->
                    actioner(CreateListingAction.UpdatePrice(newPrice))
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "Address",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.address,
                onValueChange = { newAddress ->
                    actioner(CreateListingAction.UpdateAddress(newAddress))
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            //TODO: Dropdown for city
            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 15.dp)
                    .fillMaxWidth(),
                text = "City",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = BlackText
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 15.dp)
                    .background(Color.White),
                value = viewState.city.city,
                onValueChange = { newCity ->
                    actioner(CreateListingAction.UpdatePrice(newCity))
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontSize = 14.sp
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Black,
                    unfocusedBorderColor = GrayBorderColor
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
            )

            // Product Buttons
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 32.dp)
                    .height(50.dp),
                onClick = { actioner(CreateListingAction.PostListing) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkerPurple,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(37.dp)
            ) {
                Text(
                    text = "Post your listing",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top = 20.dp, bottom = 38.dp)
                    .height(50.dp),
                onClick = { actioner(CreateListingAction.NavigateBack) },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = CancelGray,
                ),
                shape = RoundedCornerShape(37.dp),
                border = BorderStroke(1.dp, CancelGray)
            ) {
                Text(
                    text = "Cancel",
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


@Preview (showBackground = true, heightDp = 1500)
@Composable
private fun CreateListingPreviewNoPhotos() {
    CreateListingContent(viewState = CreateListingViewState())
}

@Preview (showBackground = true, heightDp = 1500)
@Composable
private fun CreateListingPreviewPhotos() {
    CreateListingContent(viewState = CreateListingViewState(
        photos = listOf(
            Photo(id = 0, photoPath = "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw98a2fe01/products/L6485676/large/L6485676.JPG")
        )
    ))
}