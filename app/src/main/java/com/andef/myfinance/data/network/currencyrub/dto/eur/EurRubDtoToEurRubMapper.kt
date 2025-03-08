package com.andef.myfinance.data.network.currencyrub.dto.eur

import com.andef.myfinance.domain.network.currencyrub.entities.EurRub

class EurRubDtoToEurRubMapper {
    fun map(eurRubDto: EurRubDto): EurRub {
        return EurRub(
            amount = eurRubDto.eurInRub.amount
        )
    }
}