package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.CurrencyRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetCurrencyRubListUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): List<CurrencyRub> {
        return repository.getCurrencyRubList()
    }
}