package com.andef.myfinance.data.database.expense.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andef.myfinance.data.database.converter.DateConverter
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory

@Entity(tableName = "expense")
@TypeConverters(DateConverter::class)
data class ExpenseModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double,
    val category: ExpenseCategory,
    val comment: String,
    val date: Long
)
