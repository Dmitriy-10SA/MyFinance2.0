package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetExpenseListUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    fun execute(startDate: Date, endDate: Date): Flow<List<Expense>> {
        return repository.getExpenseList(startDate, endDate)
    }
}