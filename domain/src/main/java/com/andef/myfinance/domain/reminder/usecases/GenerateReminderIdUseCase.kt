package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import javax.inject.Inject

class GenerateReminderIdUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend fun execute(): Int {
        return repository.generateReminderId()
    }
}