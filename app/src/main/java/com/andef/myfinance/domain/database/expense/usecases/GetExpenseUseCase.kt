package com.andef.myfinance.domain.database.expense.usecases

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    fun execute(date: Date): Flow<List<Expense>> {
        return repository.getExpense(date)
    }

    fun execute(startDate: Date, endDate: Date): Flow<List<Expense>> {
        return repository.getExpense(startDate, endDate)
    }
}