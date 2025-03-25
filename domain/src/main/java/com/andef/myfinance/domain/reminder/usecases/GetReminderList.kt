package com.andef.myfinance.domain.reminder.usecases

import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetReminderList @Inject constructor(
    private val repository: ReminderRepository
) {
    fun execute(startDate: Date, endDate: Date): Flow<List<Reminder>> {
        return repository.getReminderList(startDate, endDate)
    }
}