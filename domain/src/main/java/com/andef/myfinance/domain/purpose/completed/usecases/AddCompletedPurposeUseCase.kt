package com.andef.myfinance.domain.purpose.completed.usecases

import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose
import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import javax.inject.Inject

class AddCompletedPurposeUseCase @Inject constructor(
    private val repository: CompletedPurposeRepository
) {
    suspend fun execute(completedPurpose: CompletedPurpose) {
        repository.addCompletedPurpose(completedPurpose)
    }
}