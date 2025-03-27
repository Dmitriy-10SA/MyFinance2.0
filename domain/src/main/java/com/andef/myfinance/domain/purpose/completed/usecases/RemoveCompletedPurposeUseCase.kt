package com.andef.myfinance.domain.purpose.completed.usecases

import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import javax.inject.Inject

class RemoveCompletedPurposeUseCase @Inject constructor(
    private val repository: CompletedPurposeRepository
) {
    suspend fun execute(id: Int) {
        repository.removeCompletedPurpose(id)
    }
}