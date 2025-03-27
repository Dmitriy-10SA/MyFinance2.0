package com.andef.myfinance.data.purpose.active.mapper

import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose
import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose

internal object ActivePurposeToCompletedPurposeMapper {
    fun map(activePurpose: ActivePurpose): CompletedPurpose {
        return CompletedPurpose(
            name = activePurpose.name,
            sum = activePurpose.targetSum,
            photoUri = activePurpose.photoUri
        )
    }
}