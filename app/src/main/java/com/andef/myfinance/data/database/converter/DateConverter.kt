package com.andef.myfinance.data.database.converter

import androidx.room.TypeConverter
import com.andef.myfinance.presentation.utils.toStartOfDay
import java.util.Date

//преобразователь Date в Long (timestamp) и наоборот
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it).toStartOfDay() }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.toStartOfDay()?.time
    }
}