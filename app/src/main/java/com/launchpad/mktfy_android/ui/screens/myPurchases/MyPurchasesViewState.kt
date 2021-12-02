package com.launchpad.mktfy_android.ui.screens.myPurchases

import com.launchpad.mktfy_android.models.Listing

data class MyPurchasesViewState(
    val purchases: List<Listing> = listOf()
)