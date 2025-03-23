package com.andef.myfinance.presentation.total

import androidx.lifecycle.ViewModel
import com.andef.myfinance.domain.expense.usecases.GetFullExpenseAmountUseCase
import com.andef.myfinance.domain.income.usecases.GetFullIncomeAmountUseCase
import java.util.Date
import javax.inject.Inject

class TotalsScreenViewModel @Inject constructor(
    private val getFullIncomeAmountUseCase: GetFullIncomeAmountUseCase,
    private val getFullExpenseAmountUseCase: GetFullExpenseAmountUseCase
) : ViewModel() {
    fun getFullIncomeAmount(
        startDate: Date,
        endDate: Date
    ) = getFullIncomeAmountUseCase.execute(startDate, endDate)

    fun getFullExpenseAmount(
        startDate: Date,
        endDate: Date
    ) = getFullExpenseAmountUseCase.execute(startDate, endDate)
}