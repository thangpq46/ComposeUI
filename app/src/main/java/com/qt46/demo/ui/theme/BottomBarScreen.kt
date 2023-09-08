package com.qt46.demo.ui.theme

import androidx.compose.ui.graphics.vector.ImageVector
import com.qt46.demo.R

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val iconID: Int
){
    object Gallery : BottomBarScreen(
        "gallery",
        "Gallery",
        R.drawable.ic_pause
    )
    object Home : BottomBarScreen(
        "home",
        "Home",
        R.drawable.ic_pause
    )
    object Profile : BottomBarScreen(
        "profile",
        "Profile",
        R.drawable.ic_pause
    )
}
