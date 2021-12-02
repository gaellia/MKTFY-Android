package com.launchpad.mktfy_android.ui.screens.dashboard

import com.launchpad.mktfy_android.models.Category
import com.launchpad.mktfy_android.models.City
import com.launchpad.mktfy_android.models.Listing


data class DashboardViewState(
    val search: String = "",
    val city: City = City.CALGARY,
    val searchCity: String = "",
    val category: Category = Category.DEALS,

    val listings: List<Listing> = listOf()
)