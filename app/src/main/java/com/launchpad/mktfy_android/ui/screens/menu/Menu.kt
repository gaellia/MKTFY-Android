package com.launchpad.mktfy_android.ui.screens.menu
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.ui.theme.*


@Composable
fun Menu(
    navigateAccountInfo: () -> Unit,
    navigateBack: () -> Unit,
    navigateChangePassword: () -> Unit,
    navigateContactUs: () -> Unit,
    navigateFAQ: () -> Unit,
    navigateMyListings: () -> Unit,
    navigateMyPurchases: () -> Unit,
    navigateNotifications: () -> Unit,
    navigateLogin: () -> Unit
) {
    MenuState(
        navigateAccountInfo = navigateAccountInfo,
        navigateBack = navigateBack,
        navigateChangePassword = navigateChangePassword,
        navigateContactUs = navigateContactUs,
        navigateFAQ = navigateFAQ,
        navigateMyListings = navigateMyListings,
        navigateMyPurchases = navigateMyPurchases,
        navigateNotifications = navigateNotifications,
        navigateLogin = navigateLogin
    )
}



@Composable
private fun MenuState(
    viewModel: MenuViewModel = viewModel(),
    navigateAccountInfo: () -> Unit,
    navigateBack: () -> Unit,
    navigateChangePassword: () -> Unit,
    navigateContactUs: () -> Unit,
    navigateFAQ: () -> Unit,
    navigateMyListings: () -> Unit,
    navigateMyPurchases: () -> Unit,
    navigateNotifications: () -> Unit,
    navigateLogin: () -> Unit
){
    val viewState by viewModel.state.collectAsState()
    MenuContent(
        viewState = viewState,
        actioner = {action ->
            when(action){
                MenuAction.NavigateAccountInfo -> navigateAccountInfo()
                MenuAction.NavigateBack -> navigateBack()
                MenuAction.NavigateChangePassword -> navigateChangePassword()
                MenuAction.NavigateContactUs -> navigateContactUs()
                MenuAction.NavigateFAQ -> navigateFAQ()
                MenuAction.NavigateMyListings -> navigateMyListings()
                MenuAction.NavigateMyPurchases -> navigateMyPurchases()
                MenuAction.NavigateNotifications -> navigateNotifications()
                MenuAction.NavigateLogin -> navigateLogin()
                else -> viewModel.submitAction(action)
            }
        }
    )
}



@Composable
private fun MenuContent(
    viewState: MenuViewState = MenuViewState(),
    actioner: (MenuAction) -> Unit = {}
){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_close),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp, top = 13.dp, bottom = 13.dp)
                    .clickable { actioner(MenuAction.NavigateBack) }
            )

            // Name Banner
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
                .background(Color.White)
            ) {
                Box(modifier = Modifier
                    .padding(start = 15.dp, end = 14.dp)
                    .padding(vertical = 18.dp)
                    .size(90.dp)
                    .background(DarkerPurple, CircleShape),
                ) {
                    Text(modifier = Modifier
                        .align(Alignment.Center),
                        //TODO: Get name and then first letter of it
                        text = "M",
                        style = TextStyle.Default.copy(
                            fontFamily = openSansFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 29.sp
                        ),
                        color = Color.White
                    )
                }
                Text(modifier = Modifier
                    .align(Alignment.CenterVertically),
                    //TODO: Get name
                    text = "Mark Hamill",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = Color.Black
                )
            }

            // Menu
            Text(modifier = Modifier
                .padding(start = 15.dp, top = 30.dp),
                text ="Settings",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
            Row(modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateAccountInfo) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                    .padding(start = 15.dp),
                    text ="Account Information",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Start
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateChangePassword) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Change Password",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateMyPurchases) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "My Purchases",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateMyListings) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                    .padding(start = 15.dp),
                    text ="My Listings",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
                if (viewState.numberOfMyListings > 0) {
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .size(40.dp)
                            .background(DarkerPurple, RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = viewState.numberOfMyListings.toString(),
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            color = Color.White
                        )
                    }
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateNotifications) },
                verticalAlignment = Alignment.CenterVertically) {
                Text(modifier = Modifier
                    .padding(start = 15.dp),
                    text ="Notifications",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
                if (viewState.numberOfNotifications > 0) {
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(40.dp)
                            .background(DarkerPurple, RoundedCornerShape(10.dp))
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = viewState.numberOfNotifications.toString(),
                            style = TextStyle.Default.copy(
                                fontFamily = openSansFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            ),
                            color = Color.White
                        )
                    }
                }
            }
            Text(modifier = Modifier
                .padding(start = 15.dp, top = 30.dp),
                text ="Help",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
            Row(modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateFAQ) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "FAQ",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable { actioner(MenuAction.NavigateContactUs) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    text = "Contact Us",
                    style = TextStyle.Default.copy(
                        fontFamily = openSansFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                )
            }
        }
        //Signout
        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                actioner(MenuAction.SignOut)
                actioner(MenuAction.NavigateLogin)
            },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(modifier = Modifier
                .padding(start = 15.dp),
                text ="Sign Out",
                style = TextStyle.Default.copy(
                    fontFamily = openSansFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = SignoutTextColor
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.icon_sign_out),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.padding(end = 15.dp)
            )
        }
    }
}



@Preview
@Composable
private fun MenuPreview(){
        MenuContent(viewState = MenuViewState(numberOfMyListings = 5, numberOfNotifications = 2))
}