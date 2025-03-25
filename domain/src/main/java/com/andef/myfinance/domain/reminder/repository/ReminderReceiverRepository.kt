package com.andef.myfinance.domain.reminder.repository

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.entities.Reminder
import java.util.Date

interface ReminderReceiverRepository {
    fun notifyReminder(reminder: Reminder)
    fun changeNotifyReminder(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Date
    )

    fun cancelReminder(id: Int)
}