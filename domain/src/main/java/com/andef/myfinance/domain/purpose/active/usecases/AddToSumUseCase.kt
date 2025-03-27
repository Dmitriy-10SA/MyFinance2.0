package com.andef.myfinance.domain.purpose.active.usecases

import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import javax.inject.Inject

class AddToSumUseCase @Inject constructor(
    private val repository: ActivePurposeRepository
) {
    suspend fun execute(amount: Double) {
        repository.addToSum(amount)
    }
}