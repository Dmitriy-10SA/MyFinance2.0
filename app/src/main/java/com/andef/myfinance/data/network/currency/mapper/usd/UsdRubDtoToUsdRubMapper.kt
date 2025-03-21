package com.andef.myfinance.data.network.currency.mapper.usd

import com.andef.myfinance.data.network.currency.dto.usd.UsdRubDto
import com.andef.myfinance.domain.currency.UsdRub
import javax.inject.Inject

class UsdRubDtoToUsdRubMapper @Inject constructor() {
    fun map(usdRubDto: UsdRubDto): com.andef.myfinance.domain.currency.UsdRub {
        return com.andef.myfinance.domain.currency.UsdRub(
            amount = usdRubDto.usdInRub.amount
        )
    }
}