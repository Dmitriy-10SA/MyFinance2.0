package com.andef.myfinance.utils.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.myfinance.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal object DateFormatter {
    @Composable
    fun format(date: Date, isToday: Boolean = false): String {
        return if (isToday) {
            stringResource(R.string.today)
        } else {
            val calendar = Calendar.getInstance().apply { time = date }
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH) + 1
            val year = calendar.get(Calendar.YEAR)
            getStringDate(day, month, year)
        }
    }

    fun format(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")
        return format.format(date)
    }

    @Composable
    fun format(startDate: Date, endDate: Date): String {
        val startCalendar = Calendar.getInstance().apply { time = startDate }
        val startDay = startCalendar.get(Calendar.DAY_OF_MONTH)
        val startMonth = startCalendar.get(Calendar.MONTH) + 1
        val startYear = startCalendar.get(Calendar.YEAR)
        val start = getStringDate(startDay, startMonth, startYear)

        val endCalendar = Calendar.getInstance().apply { time = endDate }
        val endDay = endCalendar.get(Calendar.DAY_OF_MONTH)
        val endMonth = endCalendar.get(Calendar.MONTH) + 1
        val endYear = endCalendar.get(Calendar.YEAR)
        val end = getStringDate(endDay, endMonth, endYear)

        return "$start - $end"
    }

    private fun getStringDate(day: Int, month: Int, year: Int): String {
        val stringDay = if (day <= 9) {
            "0$day"
        } else {
            day.toString()
        }
        val stringMonth = if (month <= 9) {
            "0$month"
        } else {
            month.toString()
        }
        val changedYear = year % 100
        val stringYear = if (changedYear <= 9) {
            "0$changedYear"
        } else {
            changedYear.toString()
        }
        return "$stringDay.$stringMonth.$stringYear"
    }
}