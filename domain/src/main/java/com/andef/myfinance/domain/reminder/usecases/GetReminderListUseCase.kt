package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetReminderListUseCase @Inject constructor(
    private val repository: ReminderRepository
) {
    fun execute(date: Date): Flow<List<Reminder>> {
        return repository.getReminderList(date)
    }
}