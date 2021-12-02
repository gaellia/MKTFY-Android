package com.launchpad.mktfy_android.models

import java.text.NumberFormat

data class Listing(
    val id: String,
    val title: String,
    val price: Double,
    val imagePath: String
) {
    fun getFormattedPrice() = NumberFormat.getCurrencyInstance().format(price)
}