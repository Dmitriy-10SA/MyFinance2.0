package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.BtcRub
import java.util.Date
import javax.inject.Inject

class GetBtcRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.BtcRub {
        return repository.getBtcRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.BtcRub {
        return repository.getBtcRub(date)
    }
}