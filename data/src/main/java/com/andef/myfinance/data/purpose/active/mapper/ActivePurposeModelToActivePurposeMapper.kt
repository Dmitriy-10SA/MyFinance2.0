package com.andef.myfinance.data.purpose.active.mapper

import com.andef.myfinance.data.purpose.active.model.ActivePurposeModel
import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose

internal object ActivePurposeModelToActivePurposeMapper {
    fun map(activePurposeModel: ActivePurposeModel): ActivePurpose {
        return ActivePurpose(
            id = activePurposeModel.id,
            name = activePurposeModel.name,
            targetSum = activePurposeModel.targetSum,
            currentSum = activePurposeModel.currentSum,
            photoUri = activePurposeModel.photoUri
        )
    }
}