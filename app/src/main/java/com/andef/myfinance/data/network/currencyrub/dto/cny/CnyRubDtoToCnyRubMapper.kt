package com.andef.myfinance.data.network.currencyrub.dto.cny

import com.andef.myfinance.domain.network.currencyrub.entities.CnyRub

class CnyRubDtoToCnyRubMapper {
    fun map(cnyRubDto: CnyRubDto): CnyRub {
        return CnyRub(
            amount = cnyRubDto.cnyInRub.amount
        )
    }
}