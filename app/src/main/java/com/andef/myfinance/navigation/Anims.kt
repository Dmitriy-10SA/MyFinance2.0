package com.andef.myfinance.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

val slideOutRight = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(1000)
)
val slideInLeft = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(1000)
)
val slideInRight = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(1000)
)
val slideOutLeft = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(1000)
)