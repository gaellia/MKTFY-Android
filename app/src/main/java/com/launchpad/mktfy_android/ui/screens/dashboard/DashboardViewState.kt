package com.launchpad.mktfy_android.ui.screens.dashboard

import androidx.annotation.DrawableRes
import com.launchpad.mktfy_android.R
import com.launchpad.mktfy_android.models.City
import com.launchpad.mktfy_android.models.Listing


data class DashboardViewState(
    val search: String = "",
    val city: City = City.CALGARY,
    val searchCity: String = "",
    val category: Category = Category.DEALS,

    val listings: List<Listing> = listOf()
)

enum class Category (val category: String, @DrawableRes val icon: Int){
    DEALS("Deals", R.drawable.icon_deals),
    VEHICLES("Cars & Vehicles", R.drawable.icon_car),
    FURNITURE("Furniture", R.drawable.icon_furniture),
    ELECTRONICS("Electronics", R.drawable.icon_computer),
    REAL_ESTATE("Real Estate", R.drawable.icon_realestate)
}