package com.launchpad.mktfy_android.ui.screens.createListing

data class CreateListingViewState(
    val productName: String = "",
    val description: String = "",
    val category: ProductCategory? = null,
    val condition: ProductCondition = ProductCondition.USED,
    val price: String = "",
    val address: String = "",
    val city: City = City.CALGARY,

    val photos: List<Photo> = listOf(),

    val expandedCategoryMenu: Boolean = false,
    val expandedConditionMenu: Boolean = false,
    val expandedCityMenu: Boolean = false
)

data class Photo(
    val id: Int,
    val photoPath: String
)

enum class ProductCategory (val category: String){
    VEHICLES("Cars & Vehicles"),
    FURNITURE("Furniture"),
    ELECTRONICS("Electronics"),
    REAL_ESTATE("Real Estate")
}

enum class ProductCondition (val condition: String){
    USED("Used"),
    NEW("New")
}

enum class City (val city: String) {
    CALGARY("Calgary"),
    BROOKS("Brooks"),
    CAMROSE("Camrose")
}