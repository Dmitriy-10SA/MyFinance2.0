package com.andef.myfinance.presentation.income

import com.andef.myfinance.domain.income.entities.Income

sealed class IncomeState {
    data object Initial : IncomeState()
    data object Loading : IncomeState()
    data object Error : IncomeState()
    data class IncomeLoad(val income: Income?) : IncomeState()
}