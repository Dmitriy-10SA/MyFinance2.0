package com.andef.myfinance.data.network.currency.mapper.cad

import com.andef.myfinance.data.network.currency.dto.cad.CadRubDto
import com.andef.myfinance.domain.currency.CadRub
import javax.inject.Inject

class CadRubDtoToCadRubMapper @Inject constructor() {
    fun map(cadRubDto: CadRubDto): com.andef.myfinance.domain.currency.CadRub {
        return com.andef.myfinance.domain.currency.CadRub(
            amount = cadRubDto.cadInRub.amount
        )
    }
}