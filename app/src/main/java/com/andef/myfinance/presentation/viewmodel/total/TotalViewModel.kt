package com.andef.myfinance.presentation.viewmodel.total

import androidx.lifecycle.ViewModel
import com.andef.myfinance.domain.database.expense.usecases.GetFullAmountExpenseUseCase
import com.andef.myfinance.domain.database.income.usecases.GetFullAmountIncomeUseCase
import java.util.Date
import javax.inject.Inject

class TotalViewModel @Inject constructor(
    private val getFullAmountIncomeUseCase: GetFullAmountIncomeUseCase,
    private val getFullAmountExpenseUseCase: GetFullAmountExpenseUseCase
) : ViewModel() {
    fun getFullAmountIncome(
        startDate: Date,
        endDate: Date
    ) = getFullAmountIncomeUseCase.execute(startDate, endDate)

    fun getFullAmountExpense(
        startDate: Date,
        endDate: Date
    ) = getFullAmountExpenseUseCase.execute(startDate, endDate)
}