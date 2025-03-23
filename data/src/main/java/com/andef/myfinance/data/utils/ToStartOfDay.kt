package com.andef.myfinance.data.utils

import java.time.ZoneId
import java.util.Date

fun Date.toStartOfDay(incDays: Long = 0): Date {
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .atStartOfDay(ZoneId.systemDefault())
        .plusDays(incDays)
        .toInstant()
        .let { Date.from(it) }
}