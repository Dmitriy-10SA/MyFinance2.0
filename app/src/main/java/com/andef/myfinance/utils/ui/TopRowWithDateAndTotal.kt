package com.andef.myfinance.utils.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.myfinance.R
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.formatter.DateFormatter
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Composable
fun TopRowWithDateAndTotal(startDate: Date, endDate: Date, fullAmount: Double) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp, top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DateRow(startDate, endDate)
                Text(
                    text = "${stringResource(R.string.Total)} ${AmountFormatter.format(fullAmount)}",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

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