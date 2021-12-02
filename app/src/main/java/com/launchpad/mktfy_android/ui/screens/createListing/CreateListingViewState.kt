package com.launchpad.mktfy_android.ui.screens.createListing

import com.launchpad.mktfy_android.models.Category
import com.launchpad.mktfy_android.models.City
import com.launchpad.mktfy_android.models.Condition
import com.launchpad.mktfy_android.models.Photo

data class CreateListingViewState(
    val productName: String = "",
    val description: String = "",
    val category: Category? = null,
    val condition: Condition = Condition.USED,
    val price: String = "",
    val address: String = "",
    val city: City = City.CALGARY,

    val photos: List<Photo> = listOf(),

    val expandedCategoryMenu: Boolean = false,
    val expandedConditionMenu: Boolean = false,
    val expandedCityMenu: Boolean = false
)

