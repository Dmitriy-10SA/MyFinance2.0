package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import javax.inject.Inject

class GetReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend fun execute(id: Int): Reminder {
        return repository.getReminder(id)
    }
}