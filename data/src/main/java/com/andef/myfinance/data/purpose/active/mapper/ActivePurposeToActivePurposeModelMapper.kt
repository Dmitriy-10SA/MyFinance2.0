package com.andef.myfinance.data.purpose.active.mapper

import com.andef.myfinance.data.purpose.active.model.ActivePurposeModel
import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose

internal object ActivePurposeToActivePurposeModelMapper {
    fun map(activePurpose: ActivePurpose): ActivePurposeModel {
        return ActivePurposeModel(
            id = activePurpose.id,
            name = activePurpose.name,
            targetSum = activePurpose.targetSum,
            currentSum = activePurpose.currentSum,
            photoUri = activePurpose.photoUri
        )
    }
}