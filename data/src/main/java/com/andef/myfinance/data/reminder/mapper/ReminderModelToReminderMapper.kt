package com.andef.myfinance.data.reminder.mapper

import com.andef.myfinance.data.reminder.model.ReminderModel
import com.andef.myfinance.domain.reminder.entities.Reminder
import java.util.Date

object ReminderModelToReminderMapper {
    fun map(reminderModel: ReminderModel): Reminder {
        return Reminder(
            id = reminderModel.id,
            text = reminderModel.text,
            amount = reminderModel.amount,
            category = reminderModel.category,
            time = Date(reminderModel.time)
        )
    }
}