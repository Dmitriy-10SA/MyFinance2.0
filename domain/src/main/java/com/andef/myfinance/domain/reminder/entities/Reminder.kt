package com.andef.myfinance.domain.reminder.entities

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import java.util.Date

//напоминание (о расходе)
data class Reminder(
    val id: Int = 0,
    val text: String,
    val amount: Double,
    val category: ExpenseCategory,
    val time: Date
)
