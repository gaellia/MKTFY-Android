package com.launchpad.mktfy_android.models

import java.text.NumberFormat
import java.time.LocalDate

data class Listing(
    val id: String = "",
    val productName: String = "",
    val price: Double = 0.0,
    val imagePath: String = "",
    val category: Category = Category.ELECTRONICS,
    val dateCreated: LocalDate = LocalDate.of(1,1,1),
    val description: String = "",
    val condition: Condition = Condition.NEW,
    val address: String = "",
    val city: City = City.CALGARY,
    val dateSold: LocalDate = LocalDate.of(1,1,1),
    val statusOfTransaction: Status = Status.AVAILABLE,
    val sellerId: String = ""
) {
    fun getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(price)
}