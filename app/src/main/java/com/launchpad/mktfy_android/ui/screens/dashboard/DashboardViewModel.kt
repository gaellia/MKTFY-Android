package com.launchpad.mktfy_android.ui.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.launchpad.mktfy_android.core.ActionViewModel
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
        val imagePath3 = "https://scontent.fyyc3-1.fna.fbcdn.net/v/t1.15752-9/251087275_354510189696716_7718496417482136568_n.png?_nc_cat=100&ccb=1-5&_nc_sid=ae9488&_nc_ohc=0AILWCmsVoQAX_FAARW&tn=eL6LB8rpsux-L5zj&_nc_ht=scontent.fyyc3-1.fna&oh=953fff2803cad8451052df105d19061c&oe=61AC4358"
        viewModelScope.launch {
            launchSetState { copy(
                listings = listOf(
                    com.launchpad.mktfy_android.models.Listing(
                        id = "123",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "124",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "125",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "126",
                        productName = "Antique Chair",
                        price = 257.00,
                        imagePath = imagePath3
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "127",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath2
                    ),
                    com.launchpad.mktfy_android.models.Listing(
                        id = "128",
                        productName = "Playstation 4",
                        price = 340.0,
                        imagePath = imagePath
                    )

                )
            ) }
        }
    }
}