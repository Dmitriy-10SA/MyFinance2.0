package com.andef.myfinance.data.network.currency.mapper.eur

import com.andef.myfinance.data.network.currency.dto.eur.EurRubDto
import com.andef.myfinance.domain.currency.EurRub
import javax.inject.Inject

class EurRubDtoToEurRubMapper @Inject constructor() {
    fun map(eurRubDto: EurRubDto): com.andef.myfinance.domain.currency.EurRub {
        return com.andef.myfinance.domain.currency.EurRub(
            amount = eurRubDto.eurInRub.amount
        )
    }
}