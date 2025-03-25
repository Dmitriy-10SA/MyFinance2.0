package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.repository.ReminderReceiverRepository
import java.util.Date
import javax.inject.Inject

class ChangeNotifyReminderUseCase @Inject constructor(
    private val repository: ReminderReceiverRepository
) {
    fun execute(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Date
    ) {
        repository.changeNotifyReminder(id, text, amount, category, time)
    }
}