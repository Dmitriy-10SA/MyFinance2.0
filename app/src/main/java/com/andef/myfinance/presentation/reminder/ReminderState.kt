package com.andef.myfinance.presentation.reminder

import com.andef.myfinance.domain.reminder.entities.Reminder

sealed class ReminderState {
    data object Initial : ReminderState()
    data object Loading : ReminderState()
    data object Error : ReminderState()
    data class ReminderLoad(val reminder: Reminder?) : ReminderState()
}