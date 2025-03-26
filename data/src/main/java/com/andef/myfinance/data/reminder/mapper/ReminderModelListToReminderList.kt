package com.andef.myfinance.data.reminder.mapper

import com.andef.myfinance.data.reminder.model.ReminderModel
import com.andef.myfinance.domain.reminder.entities.Reminder

object ReminderModelListToReminderList {
    fun map(reminderModelList: List<ReminderModel>): List<Reminder> {
        return reminderModelList.map {
            ReminderModelToReminderMapper.map(it)
        }
    }
}