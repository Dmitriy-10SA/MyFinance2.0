package com.andef.myfinance.data.database.converter

import androidx.room.TypeConverter
import java.util.Date

//преобразователь Date в Long (timestamp) и наоборот
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}