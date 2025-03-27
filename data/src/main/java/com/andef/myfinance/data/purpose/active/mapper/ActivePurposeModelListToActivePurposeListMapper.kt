package com.andef.myfinance.data.purpose.active.mapper

import com.andef.myfinance.data.purpose.active.model.ActivePurposeModel
import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose

internal object ActivePurposeModelListToActivePurposeListMapper {
    fun map(activePurposeModelList: List<ActivePurposeModel>): List<ActivePurpose> {
        return activePurposeModelList.map {
            ActivePurposeModelToActivePurposeMapper.map(it)
        }
    }
}