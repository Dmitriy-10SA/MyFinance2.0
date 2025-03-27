package com.andef.myfinance.domain.purpose.active.usecases

import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose
import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActivePurposeListUseCase @Inject constructor(
    private val repository: ActivePurposeRepository
) {
    fun execute(): Flow<List<ActivePurpose>> {
        return repository.getActivePurposeList()
    }
}