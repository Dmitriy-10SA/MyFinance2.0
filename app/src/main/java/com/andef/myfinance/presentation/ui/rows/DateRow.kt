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
    if (startDate != endDate) {
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

private fun isToday(date: Date): Boolean {
    val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return localDate == LocalDate.now()
}