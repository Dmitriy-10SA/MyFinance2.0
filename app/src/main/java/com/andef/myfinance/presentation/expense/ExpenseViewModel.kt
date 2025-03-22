package com.andef.myfinance.presentation.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.expense.usecases.AddExpenseUseCase
import com.andef.myfinance.domain.expense.usecases.ChangeExpenseUseCase
import com.andef.myfinance.domain.expense.usecases.GetExpenseUseCase
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.presentation.income.IncomeState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class ExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val changeExpenseUseCase: ChangeExpenseUseCase,
    private val getExpenseUseCase: GetExpenseUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = ExpenseState.Error
    }

    private val _state = MutableStateFlow(ExpenseState.Initial as ExpenseState)
    val state: StateFlow<ExpenseState> = _state

    fun addExpense(income: Expense) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addExpenseUseCase.execute(income)
        }
    }

    fun changeExpense(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Date
    ) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeExpenseUseCase.execute(id, amount, category, comment, date)
        }
    }

    fun loadExpense(id: Int?) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = ExpenseState.Loading
            if (id != null) {
                val expense = withContext(Dispatchers.IO) {
                    getExpenseUseCase.execute(id)
                }
                _state.value = ExpenseState.ExpenseLoad(expense)
            } else {
                _state.value = ExpenseState.ExpenseLoad(null)
            }
        }
    }
}