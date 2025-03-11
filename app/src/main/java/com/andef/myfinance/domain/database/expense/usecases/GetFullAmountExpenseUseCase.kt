package com.andef.myfinance.domain.database.expense.usecases

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import java.util.Date
import javax.inject.Inject

class GetFullAmountExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    fun execute(startDate: Date, endDate: Date): LiveData<Double> {
        return repository.getFullAmount(startDate, endDate)
    }
}