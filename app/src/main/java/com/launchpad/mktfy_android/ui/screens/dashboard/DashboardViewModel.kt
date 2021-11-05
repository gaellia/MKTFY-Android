package com.launchpad.mktfy_android.ui.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel :
    ActionViewModel<DashboardViewState, DashboardAction>(DashboardViewState()) {
    override fun collectAction(action: DashboardAction) {
        when (action) {
            DashboardAction.NavigateCreateListing -> { }
            DashboardAction.NavigateMenu -> { }
            DashboardAction.NavigateProductDetail -> { }
            is DashboardAction.UpdateSearch -> updateSearch(action.search)
            is DashboardAction.ChangeCategory -> changeCategory(action.category)
        }
    }

    private fun updateSearch(v: String) {
        viewModelScope.launchSetState { copy(search = v) }
    }

    private fun changeCategory(c: Category) {
        viewModelScope.launchSetState { copy(category = c) }
    }

    fun getListings(){
        val imagePath = "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dw98a2fe01/products/L6485676/large/L6485676.JPG"
        val imagePath2 = "https://canadiantire.scene7.com/is/image/CanadianTire/Ct_Scooters_LIT1_Kick_&_Stunt_Scooters?scl=1"
        viewModelScope.launch {
            launchSetState { copy(
                listings = listOf(
                    Listing(
                        id = "123",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath
                    ),
                    Listing(
                        id = "124",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    Listing(
                        id = "125",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    Listing(
                        id = "126",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    Listing(
                        id = "127",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    Listing(
                        id = "128",
                        title = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath
                    )

                )
            ) }
        }
    }
}