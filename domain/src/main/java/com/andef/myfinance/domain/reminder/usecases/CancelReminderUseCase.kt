package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.repository.ReminderReceiverRepository
import javax.inject.Inject

class CancelReminderUseCase @Inject constructor(
    private val repository: ReminderReceiverRepository
) {
    fun execute(id: Int) {
        repository.cancelReminder(id)
    }
}