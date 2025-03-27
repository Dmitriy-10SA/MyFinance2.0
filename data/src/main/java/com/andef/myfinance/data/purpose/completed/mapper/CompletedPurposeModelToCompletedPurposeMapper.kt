package com.andef.myfinance.data.purpose.completed.mapper

import com.andef.myfinance.data.purpose.completed.model.CompletedPurposeModel
import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose

internal object CompletedPurposeModelToCompletedPurposeMapper {
    fun map(completedPurposeModel: CompletedPurposeModel): CompletedPurpose {
        return CompletedPurpose(
            id = completedPurposeModel.id,
            name = completedPurposeModel.name,
            sum = completedPurposeModel.sum,
            photoUri = completedPurposeModel.photoUri
        )
    }
}