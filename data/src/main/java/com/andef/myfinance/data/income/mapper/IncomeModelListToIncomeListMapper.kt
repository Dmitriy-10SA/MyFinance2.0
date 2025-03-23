package com.andef.myfinance.data.income.mapper

import com.andef.myfinance.data.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.Income

internal object IncomeModelListToIncomeListMapper {
    fun map(incomeModelList: List<IncomeModel>): List<Income> {
        return incomeModelList.map {
            IncomeModelToIncomeMapper.map(it)
        }
    }
}