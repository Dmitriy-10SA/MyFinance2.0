package com.andef.myfinance.presentation.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.usecases.GetAllReminderListUseCase
import com.andef.myfinance.domain.reminder.usecases.GetReminderListUseCase
import com.andef.myfinance.domain.reminder.usecases.RemoveReminderUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ReminderListViewModel @Inject constructor(
    private val getAllReminderListUseCase: GetAllReminderListUseCase,
    private val getReminderListUseCase: GetReminderListUseCase,
    private val removeReminderUseCase: RemoveReminderUseCase,
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    private val _remindersState = MutableStateFlow(listOf<Reminder>())
    val reminders: StateFlow<List<Reminder>> = _remindersState

    val allReminders = getAllReminderListUseCase.execute()

    private var currentJob: Job? = null

    fun setReminders(date: Date) {
        currentJob?.cancel()
        currentJob = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getReminderListUseCase.execute(date).collect {
                _remindersState.value = it
            }
        }
    }

    fun removeReminder(id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            removeReminderUseCase.execute(id)
        }
    }
}