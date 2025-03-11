package com.andef.myfinance.presentation.viewmodel.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.database.expense.usecases.AddExpenseUseCase
import com.andef.myfinance.domain.database.expense.usecases.ChangeExpenseUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class ExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val changeExpenseUseCase: ChangeExpenseUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun addExpense(expense: Expense) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addExpenseUseCase.execute(expense)
        }
    }

    fun changeExpense(
        expense: Expense,
        newAmount: Double? = null,
        newCategory: ExpenseCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    ) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeExpenseUseCase.execute(
                expense,
                newAmount,
                newCategory,
                newComment,
                newDate
            )
        }
    }
}