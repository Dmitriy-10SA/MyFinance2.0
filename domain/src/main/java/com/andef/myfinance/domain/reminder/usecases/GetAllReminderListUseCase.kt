package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllReminderListUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    fun execute(): Flow<List<Reminder>> {
        return repository.getAllReminderList()
    }
}