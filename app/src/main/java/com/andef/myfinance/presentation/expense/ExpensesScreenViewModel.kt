package com.andef.myfinance.presentation.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.usecases.GetExpenseListUseCase
import com.andef.myfinance.domain.expense.usecases.GetFullExpenseAmountUseCase
import com.andef.myfinance.domain.expense.usecases.RemoveExpenseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesScreenViewModel @Inject constructor(
    private val getExpenseListUseCase: GetExpenseListUseCase,
    private val getFullExpenseAmountUseCase: GetFullExpenseAmountUseCase,
    private val removeExpenseUseCase: RemoveExpenseUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getExpenseList(
        startDate: Date,
        endDate: Date
    ) = getExpenseListUseCase.execute(startDate, endDate)

    fun getFullAmount(
        startDate: Date,
        endDate: Date
    ) = getFullExpenseAmountUseCase.execute(startDate, endDate)

    fun removeExpense(id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            removeExpenseUseCase.execute(id)
        }
    }
}