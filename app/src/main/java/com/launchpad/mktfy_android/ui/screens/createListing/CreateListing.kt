package com.launchpad.mktfy_android.ui.screens.createListing

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.models.PriceVisualTransformation
import com.launchpad.mktfy_android.ui.components.Header
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


@OptIn(ExperimentalMaterialApi::class)
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
                    onClick = { /*TODO: Add a photo*/ },
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
                // Picture Gallery minus the cover photo
                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .background(DarkGrayBackground)
                ) {
                    items(viewState.photos.drop(1)) {
                        Image(modifier = Modifier
                            .size(125.dp),
                            painter = rememberImagePainter(
                                data = it.photoPath,
                                builder = {
                                    size(OriginalSize)
                                    scale(Scale.FIT)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    item {
                        OutlinedButton(
                            modifier = Modifier
                                .size(125.dp)
                                .padding(12.dp)
                                .size(102.dp),
                            border = BorderStroke(1.dp, DarkerPurple),
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = LightGrayBackground,
                                contentColor = DarkerPurple
                            ),
                            onClick = { /*TODO: Add a photo*/ },
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(painter = painterResource(id = R.drawable.icon_camera),
                                    contentDescription = null,
                                    tint = DarkerPurple
                                )
                                Text(modifier = Modifier
                                    .padding(top = 5.dp)
                                    .requiredWidth(80.dp),
                                    text = "Add a photo",
                                    style = TextStyle.Default.copy(
                                        fontFamily = openSansFamily,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
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
                    .background(Color.White, RoundedCornerShape(4.dp)),
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
                    .background(Color.White, RoundedCornerShape(4.dp)),
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
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(4.dp)),
                expanded = viewState.expandedCategoryMenu,
                onExpandedChange = {
                    actioner(CreateListingAction.ToggleCategoryMenu)
                }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GrayBorderColor, RoundedCornerShape(4.dp))
                    ,
                    readOnly = true,
                    value = viewState.category?.category?:"",
                    onValueChange = { },
                    placeholder = {
                        Text(
                            text = "Select a category",
                            style = MaterialTheme.typography.body1.copy(
                                color = GrayText
                            )
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewState.expandedCategoryMenu
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = Black,
                        trailingIconColor = LightPurple,
                        focusedTrailingIconColor = LightPurple,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = MaterialTheme.typography.body1
                )
                ExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                    ,
                    expanded = viewState.expandedCategoryMenu,
                    onDismissRequest = {
                        actioner(CreateListingAction.ToggleCategoryMenu)
                    }
                ) {
                    enumValues<ProductCategory>().forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (viewState.category == selectionOption) SelectedGray else Color.White
                                )
                            ,
                            onClick = {
                                actioner(CreateListingAction.UpdateCategory(selectionOption))
                                actioner(CreateListingAction.ToggleCategoryMenu)
                            }
                        ) {
                            Text(modifier = Modifier
                                .fillMaxWidth(),
                                text = selectionOption.category,
                                style = MaterialTheme.typography.body1.copy(
                                    color = if (viewState.category == selectionOption) LightPurple else Color.Black
                                )
                            )
                        }
                    }
                }
            }

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
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(4.dp)),
                expanded = viewState.expandedConditionMenu,
                onExpandedChange = {
                    actioner(CreateListingAction.ToggleConditionMenu)
                }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GrayBorderColor, RoundedCornerShape(4.dp))
                    ,
                    readOnly = true,
                    value = viewState.condition.condition,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewState.expandedConditionMenu
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = Black,
                        trailingIconColor = LightPurple,
                        focusedTrailingIconColor = LightPurple,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = MaterialTheme.typography.body1
                )
                ExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                    ,
                    expanded = viewState.expandedConditionMenu,
                    onDismissRequest = {
                        actioner(CreateListingAction.ToggleConditionMenu)
                    }
                ) {
                    enumValues<ProductCondition>().forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (viewState.condition == selectionOption) SelectedGray else Color.White
                                )
                            ,
                            onClick = {
                                actioner(CreateListingAction.UpdateCondition(selectionOption))
                                actioner(CreateListingAction.ToggleConditionMenu)
                            }
                        ) {
                            Text(modifier = Modifier
                                .fillMaxWidth(),
                                text = selectionOption.condition,
                                style = MaterialTheme.typography.body1.copy(
                                    color = if (viewState.condition == selectionOption) LightPurple else Color.Black
                                )
                            )
                        }
                    }
                }
            }

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
                    .background(Color.White, RoundedCornerShape(4.dp)),
                placeholder = {
                      Text(text = "Type the price",
                        style = MaterialTheme.typography.body1.copy(
                            color = GrayText
                        )
                      )
                },
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                visualTransformation = PriceVisualTransformation()
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
                    .background(Color.White, RoundedCornerShape(4.dp)),
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
            ExposedDropdownMenuBox(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(4.dp)),
                expanded = viewState.expandedCityMenu,
                onExpandedChange = {
                    actioner(CreateListingAction.ToggleCityMenu)
                }
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GrayBorderColor, RoundedCornerShape(4.dp))
                    ,
                    readOnly = true,
                    value = viewState.city.city,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewState.expandedCityMenu
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        textColor = Black,
                        trailingIconColor = LightPurple,
                        focusedTrailingIconColor = LightPurple,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = MaterialTheme.typography.body1
                )
                ExposedDropdownMenu(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                    ,
                    expanded = viewState.expandedCityMenu,
                    onDismissRequest = {
                        actioner(CreateListingAction.ToggleCityMenu)
                    }
                ) {
                    enumValues<City>().forEach { selectionOption ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (viewState.city == selectionOption) SelectedGray else Color.White
                                )
                            ,
                            onClick = {
                                actioner(CreateListingAction.UpdateCity(selectionOption))
                                actioner(CreateListingAction.ToggleCityMenu)
                            }
                        ) {
                            Text(modifier = Modifier
                                .fillMaxWidth(),
                                text = selectionOption.city,
                                style = MaterialTheme.typography.body1.copy(
                                    color = if (viewState.city == selectionOption) LightPurple else Color.Black
                                )
                            )
                        }
                    }
                }
            }

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
            Photo(id = 0, photoPath = "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw98a2fe01/products/L6485676/large/L6485676.JPG"),
            Photo(id = 1, photoPath = "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw98a2fe01/products/L6485676/large/L6485676.JPG")
        )
    ))
}