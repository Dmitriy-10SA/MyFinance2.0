package com.andef.myfinance.old_presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun getScreenWidth(): Int {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    return screenWidth
}