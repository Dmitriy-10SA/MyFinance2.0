package com.andef.myfinance.data.utils

import androidx.room.TypeConverter
import java.util.Date

//преобразователь Date в Long (timestamp) и наоборот
internal class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it).toStartOfDay() }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.toStartOfDay()?.time
    }
}