package com.andef.myfinance.data.network.currency.mapper.usd

import com.andef.myfinance.data.network.currency.dto.usd.UsdRubDto
import com.andef.myfinance.domain.network.currency.entities.usd.UsdRub

class UsdRubDtoToUsdRubMapper {
    fun map(usdRubDto: UsdRubDto): UsdRub {
        return UsdRub(
            amount = usdRubDto.usdInRub.amount
        )
    }
}