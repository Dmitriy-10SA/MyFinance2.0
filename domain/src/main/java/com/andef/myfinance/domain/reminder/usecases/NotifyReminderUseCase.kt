package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderReceiverRepository
import javax.inject.Inject

class NotifyReminderUseCase @Inject constructor(
    private val repository: ReminderReceiverRepository
) {
    fun execute(reminder: Reminder) {
        repository.notifyReminder(reminder)
    }
}