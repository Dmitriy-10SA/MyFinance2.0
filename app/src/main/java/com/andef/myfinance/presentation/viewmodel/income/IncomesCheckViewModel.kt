package com.andef.myfinance.presentation.viewmodel.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.income.usecases.GetFullAmountIncomeUseCase
import com.andef.myfinance.domain.database.income.usecases.GetIncomesUseCase
import com.andef.myfinance.domain.database.income.usecases.RemoveIncomeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomesCheckViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getFullAmountUseCase: GetFullAmountIncomeUseCase,
    private val removeIncomeUseCase: RemoveIncomeUseCase
) : ViewModel() {
    fun getIncomes(
        startDate: Date,
        endDate: Date
    ) = getIncomesUseCase.execute(startDate, endDate)

    fun getFullAmount(
        startDate: Date,
        endDate: Date
    ) = getFullAmountUseCase.execute(startDate, endDate)

    fun removeIncome(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeIncomeUseCase.execute(id)
        }
    }
}