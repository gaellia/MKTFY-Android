package com.launchpad.mktfy_android.ui.screens.dashboard

import java.text.NumberFormat


data class DashboardViewState(
    val search: String = "",
    val city: City = City.CALGARY,
    val searchCity: String = "",
    val category: Category = Category.DEALS,

    val listings: List<Listing> = listOf()
)

data class Listing(
    val id: String,
    val title: String,
    val price: Double,
    val imagePath: String
) {
    fun getFormattedPrice() = NumberFormat.getCurrencyInstance().format(price)
}

enum class City {
    CALGARY, BROOKS, CAMROSE
}

enum class Category {
    DEALS, VEHICLES, FURNITURE, ELECTRONICS, REAL_ESTATE
}