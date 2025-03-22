package com.andef.myfinance.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

val slideOutRight = slideOutHorizontally(
    targetOffsetX = { it },
    animationSpec = tween(800, easing = FastOutSlowInEasing)
)
val slideInLeft = slideInHorizontally(
    initialOffsetX = { it },
    animationSpec = tween(800, easing = FastOutSlowInEasing)
)
val slideInRight = slideInHorizontally(
    initialOffsetX = { -it },
    animationSpec = tween(800, easing = FastOutSlowInEasing)
)
val slideOutLeft = slideOutHorizontally(
    targetOffsetX = { -it },
    animationSpec = tween(800, easing = FastOutSlowInEasing)
)

val slideInDown = slideInVertically(
    initialOffsetY = { -it },
    animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
)

val slideInUp = slideInVertically(
    initialOffsetY = { it },
    animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
)

val slideOutUp = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
)

val slideOutDown = slideOutVertically(
    targetOffsetY = { -it },
    animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
)

val rangePickerAnim = (slideInLeft).togetherWith(slideOutLeft)

val defaultScreenAnim = (slideInRight).togetherWith(slideOutRight)