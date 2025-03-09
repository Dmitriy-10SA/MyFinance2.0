package com.andef.myfinance.data.database.income.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andef.myfinance.data.database.converter.DateConverter
import com.andef.myfinance.domain.database.income.entities.IncomeCategory

@Entity(tableName = "income")
@TypeConverters(DateConverter::class)
data class IncomeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: IncomeCategory,
    val comment: String,
    val date: Long
)
