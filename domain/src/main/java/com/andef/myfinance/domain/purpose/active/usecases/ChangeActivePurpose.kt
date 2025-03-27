package com.andef.myfinance.domain.purpose.active.usecases

import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import javax.inject.Inject

class ChangeActivePurpose @Inject constructor(
    private val repository: ActivePurposeRepository
) {
    suspend fun execute(
        id: Int,
        name: String,
        targetSum: Double,
        currentSum: Double,
        photoUri: String
    ) {
        repository.changeActivePurpose(id, name, targetSum, currentSum, photoUri)
    }
}