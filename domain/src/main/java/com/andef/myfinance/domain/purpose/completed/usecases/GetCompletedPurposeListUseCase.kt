package com.andef.myfinance.domain.purpose.completed.usecases

import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose
import com.andef.myfinance.domain.purpose.completed.repository.CompletedPurposeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCompletedPurposeListUseCase @Inject constructor(
    private val repository: CompletedPurposeRepository
) {
    fun execute(): Flow<List<CompletedPurpose>> {
        return repository.getCompletedPurposeList()
    }
}