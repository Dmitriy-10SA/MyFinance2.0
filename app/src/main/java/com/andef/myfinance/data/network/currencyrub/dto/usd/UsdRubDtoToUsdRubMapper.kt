package com.andef.myfinance.data.network.currencyrub.dto.usd

import com.andef.myfinance.domain.network.currencyrub.entities.UsdRub

class UsdRubDtoToUsdRubMapper {
    fun map(usdRubDto: UsdRubDto): UsdRub {
        return UsdRub(
            amount = usdRubDto.usdInRub.amount
        )
    }
}