package com.andef.myfinance.domain.purpose.active.usecases

import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose
import com.andef.myfinance.domain.purpose.active.repository.ActivePurposeRepository
import javax.inject.Inject

class AddActivePurposeUseCase @Inject constructor(
    private val repository: ActivePurposeRepository
) {
    suspend fun execute(activePurpose: ActivePurpose) {
        repository.addActivePurpose(activePurpose)
    }
}