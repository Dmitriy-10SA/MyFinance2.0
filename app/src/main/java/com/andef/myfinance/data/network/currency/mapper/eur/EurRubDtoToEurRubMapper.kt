package com.andef.myfinance.data.network.currency.mapper.eur

import com.andef.myfinance.data.network.currency.dto.eur.EurRubDto
import com.andef.myfinance.domain.network.currency.entities.eur.EurRub

class EurRubDtoToEurRubMapper {
    fun map(eurRubDto: EurRubDto): EurRub {
        return EurRub(
            amount = eurRubDto.eurInRub.amount
        )
    }
}