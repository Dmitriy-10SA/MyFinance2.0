package com.andef.myfinance.presentation.ui.income

import com.andef.myfinance.domain.database.income.entities.Income

sealed class IncomesState {
    data object Initial : IncomesState()
    data object Loading : IncomesState()
    data class Incomes(val incomes: List<Income>) : IncomesState()
    data object Error : IncomesState()
}