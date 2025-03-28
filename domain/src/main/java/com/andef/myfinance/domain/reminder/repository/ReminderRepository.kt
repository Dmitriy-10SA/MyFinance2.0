package com.andef.myfinance.domain.reminder.repository

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.entities.Reminder
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ReminderRepository {
    suspend fun addReminder(reminder: Reminder)
    suspend fun changeReminder(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Date
    )

    suspend fun removeReminder(id: Int)
    fun getReminderList(date: Date): Flow<List<Reminder>>
    suspend fun getReminder(id: Int): Reminder
    fun getAllReminderList(): Flow<List<Reminder>>
    suspend fun generateReminderId(): Int
}