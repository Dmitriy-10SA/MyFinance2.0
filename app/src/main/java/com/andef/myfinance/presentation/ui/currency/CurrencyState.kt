package com.andef.myfinance.presentation.ui.currency

import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

sealed class CurrencyState {
    data object Initial : CurrencyState()
    data object Loading : CurrencyState()
    data object Error : CurrencyState()
    data class CurrencyFirstPart(val currency: List<CurrencyRub>) : CurrencyState()
    data class CurrencySecondPart(val currency: List<CurrencyRub>) : CurrencyState()
    data class CurrencyThirdPart(val currency: List<CurrencyRub>) : CurrencyState()
    data class CurrencyFourthPart(val currency: List<CurrencyRub>) : CurrencyState()
}