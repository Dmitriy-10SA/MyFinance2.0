package com.andef.myfinance.domain.database.expense.usecases

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    fun execute(startDate: Date, endDate: Date): LiveData<List<Expense>> {
        return repository.getExpenses(startDate, endDate)
    }
}