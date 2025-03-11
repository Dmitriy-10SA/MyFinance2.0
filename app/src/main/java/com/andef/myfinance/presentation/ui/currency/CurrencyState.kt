package com.andef.myfinance.presentation.ui.currency

import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

sealed class CurrencyState {
    data object Initial : CurrencyState()
    data object Loading : CurrencyState()
    data object Error : CurrencyState()
    data class Currency(val currency: List<CurrencyRub>) : CurrencyState()
}