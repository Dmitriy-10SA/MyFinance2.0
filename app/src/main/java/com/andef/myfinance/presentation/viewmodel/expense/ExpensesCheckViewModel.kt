package com.andef.myfinance.presentation.viewmodel.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.expense.usecases.GetExpensesUseCase
import com.andef.myfinance.domain.database.expense.usecases.GetFullAmountUseCase
import com.andef.myfinance.presentation.ui.expense.ExpensesState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesCheckViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val getFullAmountUseCase: GetFullAmountUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ExpensesState>()
    val state: LiveData<ExpensesState>
        get() = _state

    fun setDates(startDate: Date, endDate: Date) {
        expensesSubscribe(startDate, endDate)
        amountSubscribe(startDate, endDate)
    }

    private fun amountSubscribe(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            getFullAmountUseCase.execute(startDate, endDate)
                .onStart {
                    _state.value = ExpensesState.Loading
                }
                .catch {
                    _state.value = ExpensesState.Error
                }
                .collect {
                    _state.value = ExpensesState.Amount(it)
                }
        }
    }

    private fun expensesSubscribe(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            getExpensesUseCase.execute(startDate, endDate)
                .onStart {
                    _state.value = ExpensesState.Loading
                }
                .catch {
                    _state.value = ExpensesState.Error
                }
                .collect {
                    _state.value = ExpensesState.Expenses(it)
                }
        }
    }
}