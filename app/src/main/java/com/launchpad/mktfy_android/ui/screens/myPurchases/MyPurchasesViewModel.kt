package com.launchpad.mktfy_android.ui.screens.myPurchases

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

class MyPurchasesViewModel :
    ActionViewModel<MyPurchasesViewState, MyPurchasesAction>(MyPurchasesViewState()) {
    override fun collectAction(action: MyPurchasesAction) {
        when (action) {
            MyPurchasesAction.NavigateBack -> { }
            is MyPurchasesAction.NavigatePickupInformation -> { }
        }
    }

    fun getPurchases() {
        val imagePath = "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw98a2fe01/products/L6485676/large/L6485676.JPG"
        val imagePath2 = "https://canadiantire.scene7.com/is/image/CanadianTire/Ct_Scooters_LIT1_Kick_&_Stunt_Scooters?scl=1"
        viewModelScope.launch {
            launchSetState { copy(
                purchases = listOf(
                    com.launchpad.mktfy_android.models.Listing(
                        id = "123",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath,
                        dateSold = LocalDate.of(2020, 11, 22)
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "124",
                        productName = "Scooter",
                        price = 10.5,
                        imagePath = imagePath2,
                        dateSold = LocalDate.of(2020, 9, 7)
                    )
                )
            ) }
        }
    }
}