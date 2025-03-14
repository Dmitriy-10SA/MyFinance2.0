package com.andef.myfinance.presentation.ui.currency

import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

sealed class CurrencyState {
    data object Initial : CurrencyState()
    data object Loading : CurrencyState()
    data object Error : CurrencyState()

    data class CurrencyFirstPartWithPercent(val pair: List<Pair<CurrencyRub, Double>>) :
        CurrencyState()

    data class CurrencySecondPartWithPercent(val pair: List<Pair<CurrencyRub, Double>>) :
        CurrencyState()

    data class CurrencyThirdPartWithPercent(val pair: List<Pair<CurrencyRub, Double>>) :
        CurrencyState()

    data class CurrencyFourthPartWithPercent(val pair: List<Pair<CurrencyRub, Double>>) :
        CurrencyState()
}