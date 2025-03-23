package com.andef.myfinance.presentation.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.income.usecases.GetFullIncomeAmountUseCase
import com.andef.myfinance.domain.income.usecases.GetIncomeListUseCase
import com.andef.myfinance.domain.income.usecases.RemoveIncomeUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomesScreenViewModel @Inject constructor(
    private val getIncomeListUseCase: GetIncomeListUseCase,
    private val getFullIncomeAmountUseCase: GetFullIncomeAmountUseCase,
    private val removeIncomeUseCase: RemoveIncomeUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun getIncomeList(
        startDate: Date,
        endDate: Date
    ) = getIncomeListUseCase.execute(startDate, endDate)

    fun getFullAmount(
        startDate: Date,
        endDate: Date
    ) = getFullIncomeAmountUseCase.execute(startDate, endDate)

    fun removeIncome(id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            removeIncomeUseCase.execute(id)
        }
    }
}