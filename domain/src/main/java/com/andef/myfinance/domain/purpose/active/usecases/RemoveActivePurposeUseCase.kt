package com.andef.myfinance.domain.purpose.active.usecases

import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import javax.inject.Inject

class RemoveActivePurposeUseCase @Inject constructor(
    private val repository: ActivePurposeRepository
) {
    suspend fun execute(id: Int) {
        repository.removeActivePurpose(id)
    }
}