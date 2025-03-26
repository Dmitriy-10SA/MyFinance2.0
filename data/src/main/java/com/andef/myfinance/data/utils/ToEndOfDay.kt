package com.andef.myfinance.data.utils

import java.time.ZoneId
import java.util.Date

fun Date.toEndOfDay(incDays: Long = 0): Date {
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .atTime(23, 59, 59, 999_999_999)
        .plusDays(incDays)
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .let { Date.from(it) }
}