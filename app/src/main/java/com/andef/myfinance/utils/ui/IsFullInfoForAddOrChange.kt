package com.andef.myfinance.utils.ui

import androidx.compose.runtime.MutableState

fun isFullInfoForAddOrChange(amount: MutableState<String>): Boolean {
    return amount.value.isNotEmpty()
}