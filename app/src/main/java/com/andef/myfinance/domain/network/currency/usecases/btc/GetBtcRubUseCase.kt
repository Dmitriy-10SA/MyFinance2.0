package com.andef.myfinance.domain.network.currency.usecases.btc

import com.andef.myfinance.domain.network.currency.entities.btc.BtcRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetBtcRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): BtcRub {
        return repository.getBtcRub()
    }

    suspend fun execute(date: Date): BtcRub {
        return repository.getBtcRub(date)
    }
}