package com.launchpad.mktfy_android.models

import androidx.annotation.DrawableRes
import com.launchpad.mktfy_android.R

enum class Category (val category: String, @DrawableRes val icon: Int){
    DEALS("Deals", R.drawable.icon_deals),
    VEHICLES("Cars & Vehicles", R.drawable.icon_car),
    FURNITURE("Furniture", R.drawable.icon_furniture),
    ELECTRONICS("Electronics", R.drawable.icon_computer),
    REAL_ESTATE("Real Estate", R.drawable.icon_realestate)
}