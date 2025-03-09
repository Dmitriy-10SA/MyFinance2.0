package com.andef.myfinance.data.network.currency.mapper.cny

import com.andef.myfinance.data.network.currency.dto.cny.CnyRubDto
import com.andef.myfinance.domain.network.currency.entities.cny.CnyRub

class CnyRubDtoToCnyRubMapper {
    fun map(cnyRubDto: CnyRubDto): CnyRub {
        return CnyRub(
            amount = cnyRubDto.cnyInRub.amount
        )
    }
}