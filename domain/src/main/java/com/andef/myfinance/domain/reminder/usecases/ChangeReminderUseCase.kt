package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import java.util.Date
import javax.inject.Inject

class ChangeReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend fun execute(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Date
    ) {
        repository.changeReminder(id, text, amount, category, time)
    }
}