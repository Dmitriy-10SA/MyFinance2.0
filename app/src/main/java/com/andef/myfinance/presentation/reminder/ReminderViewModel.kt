package com.andef.myfinance.presentation.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.domain.reminder.usecases.AddReminderUseCase
import com.andef.myfinance.domain.reminder.usecases.ChangeReminderUseCase
import com.andef.myfinance.domain.reminder.usecases.GenerateReminderIdUseCase
import com.andef.myfinance.domain.reminder.usecases.GetReminderUseCase
import com.andef.myfinance.domain.reminder.usecases.RemoveReminderUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class ReminderViewModel @Inject constructor(
    private val addReminderUseCase: AddReminderUseCase,
    private val changeReminderUseCase: ChangeReminderUseCase,
    private val getReminderUseCase: GetReminderUseCase,
    private val generateReminderIdUseCase: GenerateReminderIdUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = ReminderState.Error
    }

    private val _state = MutableStateFlow(ReminderState.Initial as ReminderState)
    val state: StateFlow<ReminderState> = _state

    fun addReminder(reminder: Reminder) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addReminderUseCase.execute(reminder)
        }
    }

    suspend fun generateReminderId() = generateReminderIdUseCase.execute()

    fun changeReminder(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Date
    ) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeReminderUseCase.execute(
                id = id,
                amount = amount,
                category = category,
                text = comment,
                time = date
            )
        }
    }

    fun loadReminder(id: Int?) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = ReminderState.Loading
            if (id != null) {
                val reminder = withContext(Dispatchers.IO) {
                    getReminderUseCase.execute(id)
                }
                _state.value = ReminderState.ReminderLoad(reminder)
            } else {
                _state.value = ReminderState.ReminderLoad(null)
            }
        }
    }
}