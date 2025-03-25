package com.andef.myfinance.data.reminder.mapper

import com.andef.myfinance.data.reminder.model.ReminderModel
import com.andef.myfinance.domain.reminder.entities.Reminder

object ReminderToReminderModelMapper {
    fun map(reminder: Reminder): ReminderModel {
        return ReminderModel(
            id = reminder.id,
            text = reminder.text,
            amount = reminder.amount,
            category = reminder.category,
            time = reminder.time.time
        )
    }
}