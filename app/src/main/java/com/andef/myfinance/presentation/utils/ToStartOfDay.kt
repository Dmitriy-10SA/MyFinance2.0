package com.andef.myfinance.presentation.utils

import java.time.ZoneId
import java.util.Date

fun Date.toStartOfDay(): Date {
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
        .let { Date.from(it) }
}