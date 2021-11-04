package com.launchpad.mktfy_android.ui.screens.dashboard

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.theme.*


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
    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(LightGrayBackground)
        ) {
            // Header
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(DarkerPurple)
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_24px),
                        tint = Color.Unspecified,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(bottom = 18.dp, top = 18.dp, start = 15.dp)
                    )
                    Text(modifier = Modifier
                        .padding(start = 15.dp)
                        .align(Alignment.CenterVertically),
                        text = "|",
                        style = TextStyle.Default.copy(
                            fontSize = 20.sp,
                            color = Gray
                        )
                    )
                    TextField(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        value = viewState.search,
                        onValueChange = {newSearch -> actioner(DashboardAction.UpdateSearch(newSearch))},
                        placeholder = {
                            Text(
                                text = buildAnnotatedString {
                                    append("Search on")
                                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(" MKTFY")
                                    }
                                },
                                style = TextStyle.Default.copy(
                                    fontFamily = openSansFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.sp,
                                    color = Gray
                                )
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Gray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        trailingIcon = {Icon(
                            painterResource(id = R.drawable.search_24px),
                            contentDescription = null,
                            tint = Gray)},
                        singleLine = true
                    )
                }
            }
            // Categories
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(129.dp)
                .background(Color.White)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(modifier = Modifier
                        .padding(start = 15.dp),
                        text ="Browse Categories",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                    //TODO: Dropdown menu
                    Text(modifier = Modifier
                        .padding(end = 15.dp),
                        text = "City",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = DarkerPurple
                        )
                    )
                }
                LazyRow(
                    contentPadding = PaddingValues(top = 17.dp, bottom = 20.dp, start = 15.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    content = {
                        Category.values().forEach {
                            item {
                                Column(modifier = Modifier
                                    .clickable {
                                        actioner(DashboardAction.ChangeCategory(it))
                                    }) {
                                    Icon(
                                        painter = painterResource(id = it.icon),
                                        contentDescription = null,
                                        tint = if (viewState.category == it) DarkerPurple else YellowOrange,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                    Text(modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                        text = it.category,
                                        style = TextStyle.Default.copy(
                                            fontFamily = openSansFamily,
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 10.sp,
                                            color = Color.Black
                                        )
                                    )
                                }
                            }
                        }
                    }
                )
            }
            // Listings
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 3.dp)
                .background(Color.White)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(modifier = Modifier
                        .padding(start = 15.dp),
                        text = if (viewState.category == Category.DEALS) viewState.category.category+" for you" else viewState.category.category,
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                    //TODO: Change City
                    Text(modifier = Modifier
                        .padding(end = 15.dp),
                        text = "City",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>) {

}


@Preview
@Composable
private fun DashboardPreview() {
    DashboardContent(viewState = DashboardViewState())

}