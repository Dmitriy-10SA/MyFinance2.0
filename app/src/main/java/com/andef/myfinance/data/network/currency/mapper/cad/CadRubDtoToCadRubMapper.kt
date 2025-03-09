package com.andef.myfinance.data.network.currency.mapper.cad

import com.andef.myfinance.data.network.currency.dto.cad.CadRubDto
import com.andef.myfinance.domain.network.currency.entities.cad.CadRub

class CadRubDtoToCadRubMapper {
    fun map(cadRubDto: CadRubDto): CadRub {
        return CadRub(
            amount = cadRubDto.cadInRub.amount
        )
    }
}