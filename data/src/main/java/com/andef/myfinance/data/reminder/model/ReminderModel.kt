package com.andef.myfinance.data.reminder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.andef.myfinance.data.utils.DateConverter
import com.andef.myfinance.domain.expense.entities.ExpenseCategory

//напоминание (о расходе)
@Entity(tableName = "reminder")
@TypeConverters(DateConverter::class)
data class ReminderModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    val amount: Double,
    val category: ExpenseCategory,
    val time: Long
)