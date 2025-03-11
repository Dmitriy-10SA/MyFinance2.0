package com.andef.myfinance.presentation.ui.rows

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.andef.myfinance.presentation.formatter.DateFormatter
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Composable
fun DateRow(startDate: Date, endDate: Date) {
    if (!isSameDay(startDate, endDate)) {
        Text(
            text = DateFormatter.format(startDate, endDate),
            color = MaterialTheme.colorScheme.onBackground
        )
    } else if (!isToday(startDate)) {
        Text(
            text = DateFormatter.format(startDate),
            color = MaterialTheme.colorScheme.onBackground
        )
    } else {
        Text(
            text = DateFormatter.format(startDate, true),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun isSameDay(date1: Date, date2: Date): Boolean {
    val zone = ZoneId.systemDefault()
    val localDate1 = date1.toInstant().atZone(zone).toLocalDate()
    val localDate2 = date2.toInstant().atZone(zone).toLocalDate()
    return localDate1 == localDate2
}

private fun isToday(date: Date): Boolean {
    val systemZone = ZoneId.systemDefault()
    val localDate = date.toInstant().atZone(systemZone).toLocalDate()
    val today = LocalDate.now(systemZone)
    return localDate == today
}