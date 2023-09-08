package com.qt46.demo

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int,val iconID:Int) {
    object Gallery : Screen("gallery", R.string.gallery,R.drawable.ic_gallery)
    object Home : Screen("home", R.string.home,R.drawable.ic_home)
    object Profile : Screen("profile", R.string.profile,R.drawable.ic_profile)
}