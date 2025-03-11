package com.andef.myfinance.data.network.currency.mapper.eur

import com.andef.myfinance.data.network.currency.dto.eur.EurRubDto
import com.andef.myfinance.domain.network.currency.entities.eur.EurRub
import javax.inject.Inject

class EurRubDtoToEurRubMapper @Inject constructor() {
    fun map(eurRubDto: EurRubDto): EurRub {
        return EurRub(
            amount = eurRubDto.eurInRub.amount
        )
    }
}