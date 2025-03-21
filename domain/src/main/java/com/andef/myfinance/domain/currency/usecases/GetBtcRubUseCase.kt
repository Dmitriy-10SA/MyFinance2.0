package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetBtcRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CurrencyRub.Btc {
        return repository.getBtcRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Btc {
        return repository.getBtcRub(date)
    }
}