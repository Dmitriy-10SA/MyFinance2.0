package com.andef.myfinance.presentation.viewmodel.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.expense.usecases.GetExpensesUseCase
import com.andef.myfinance.domain.database.expense.usecases.GetFullAmountExpenseUseCase
import com.andef.myfinance.domain.database.expense.usecases.RemoveExpenseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpensesCheckViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val getFullAmountUseCase: GetFullAmountExpenseUseCase,
    private val removeExpenseUseCase: RemoveExpenseUseCase
) : ViewModel() {
    fun getExpenses(
        startDate: Date,
        endDate: Date
    ) = getExpensesUseCase.execute(startDate, endDate)

    fun getFullAmount(
        startDate: Date,
        endDate: Date
    ) = getFullAmountUseCase.execute(startDate, endDate)

    fun removeExpense(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeExpenseUseCase.execute(id)
        }
    }
}