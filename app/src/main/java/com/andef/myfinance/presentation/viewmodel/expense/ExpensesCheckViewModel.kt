package com.andef.myfinance.presentation.viewmodel.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.usecases.GetExpensesUseCase
import com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase
import com.andef.myfinance.domain.expense.usecases.RemoveExpenseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesCheckViewModel @Inject constructor(
    private val getExpensesUseCase: com.andef.myfinance.domain.expense.usecases.GetExpensesUseCase,
    private val getFullAmountUseCase: com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase,
    private val removeExpenseUseCase: com.andef.myfinance.domain.expense.usecases.RemoveExpenseUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getExpenses(
        startDate: Date,
        endDate: Date
    ) = getExpensesUseCase.execute(startDate, endDate)

    fun getFullAmount(
        startDate: Date,
        endDate: Date
    ) = getFullAmountUseCase.execute(startDate, endDate)

    fun removeExpense(id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            removeExpenseUseCase.execute(id)
        }
    }
}