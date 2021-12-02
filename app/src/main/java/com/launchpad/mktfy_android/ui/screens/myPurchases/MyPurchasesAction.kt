package com.launchpad.mktfy_android.ui.screens.myPurchases

sealed class MyPurchasesAction {
    object NavigateBack: MyPurchasesAction()
    data class NavigatePickupInformation(val listingId: String): MyPurchasesAction()
}