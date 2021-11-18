package com.launchpad.mktfy_android.ui.screens.createListing

data class CreateListingViewState(
    val productName: String = "",
    val description: String = "",
    val category: ProductCategory = ProductCategory.VEHICLES,
    val condition: ProductCondition = ProductCondition.USED,
    val price: String = "",
    val address: String = "",
    val city: City = City.CALGARY
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

enum class City {
    CALGARY, BROOKS, CAMROSE
}