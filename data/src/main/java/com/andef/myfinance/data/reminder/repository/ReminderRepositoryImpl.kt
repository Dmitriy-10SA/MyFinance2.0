package com.andef.myfinance.data.reminder.repository

import com.andef.myfinance.data.reminder.datasource.ReminderDao
import com.andef.myfinance.data.reminder.mapper.ReminderModelListToReminderList
import com.andef.myfinance.data.reminder.mapper.ReminderModelToReminderMapper
import com.andef.myfinance.data.reminder.mapper.ReminderToReminderModelMapper
import com.andef.myfinance.data.utils.toEndOfDay
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.repository.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(
    private val dao: ReminderDao
) : ReminderRepository {
    override suspend fun addReminder(reminder: Reminder) {
        dao.addReminder(ReminderToReminderModelMapper.map(reminder))
    }

    override suspend fun changeReminder(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Date
    ) {
        dao.changeReminder(id, text, amount, category, time.time)
    }

    override suspend fun removeReminder(id: Int) {
        dao.removeReminder(id)
    }

    override fun getReminderList(date: Date): Flow<List<Reminder>> {
        return dao.getReminderList(date.toEndOfDay().time, date.toStartOfDay().time).map {
            ReminderModelListToReminderList.map(it)
        }
    }


    override suspend fun getReminder(id: Int): Reminder {
        return ReminderModelToReminderMapper.map(dao.getReminder(id))
    }

    override fun getAllReminderList(): Flow<List<Reminder>> {
        return dao.getAllReminderList().map {
            ReminderModelListToReminderList.map(it)
        }
    }

    override suspend fun generateReminderId(): Int {
        return dao.generateId()
    }
}