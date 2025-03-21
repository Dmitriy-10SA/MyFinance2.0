package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetFullExpenseAmountUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    fun execute(startDate: Date, endDate: Date): Flow<Double> {
        return repository.getFullExpenseAmount(startDate, endDate)
    }
}