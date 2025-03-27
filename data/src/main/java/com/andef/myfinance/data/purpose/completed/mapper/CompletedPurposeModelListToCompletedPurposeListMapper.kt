package com.andef.myfinance.data.purpose.completed.mapper

import com.andef.myfinance.data.purpose.completed.model.CompletedPurposeModel
import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose

internal object CompletedPurposeModelListToCompletedPurposeListMapper {
    fun map(completedPurposeModelList: List<CompletedPurposeModel>): List<CompletedPurpose> {
        return completedPurposeModelList.map {
            CompletedPurposeModelToCompletedPurposeMapper.map(it)
        }
    }
}