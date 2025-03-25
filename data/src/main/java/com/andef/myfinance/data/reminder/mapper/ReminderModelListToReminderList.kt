package com.andef.myfinance.data.reminder.mapper

import com.andef.myfinance.data.reminder.model.ReminderModel
import com.andef.myfinance.domain.reminder.entities.Reminder
import java.util.Date

object ReminderModelListToReminderList {
    private fun map(reminderModel: ReminderModel): Reminder {
        return Reminder(
            id = reminderModel.id,
            text = reminderModel.text,
            amount = reminderModel.amount,
            category = reminderModel.category,
            time = Date(reminderModel.time)
        )
    }

    fun map(reminderModelList: List<ReminderModel>): List<Reminder> {
        return reminderModelList.map {
            this.map(it)
        }
    }
}