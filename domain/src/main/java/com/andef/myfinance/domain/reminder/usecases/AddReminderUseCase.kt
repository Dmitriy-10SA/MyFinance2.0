package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import javax.inject.Inject

class AddReminderUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend fun execute(reminder: Reminder) {
        repository.addReminder(reminder)
    }
}