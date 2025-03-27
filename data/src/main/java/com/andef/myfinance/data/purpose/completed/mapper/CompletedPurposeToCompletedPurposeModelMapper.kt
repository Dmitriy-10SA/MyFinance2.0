package com.andef.myfinance.data.purpose.completed.mapper

import com.andef.myfinance.data.purpose.completed.model.CompletedPurposeModel
import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose

internal object CompletedPurposeToCompletedPurposeModelMapper {
    fun map(completedPurpose: CompletedPurpose): CompletedPurposeModel {
        return CompletedPurposeModel(
            id = completedPurpose.id,
            name = completedPurpose.name,
            sum = completedPurpose.sum,
            photoUri = completedPurpose.photoUri
        )
    }
}