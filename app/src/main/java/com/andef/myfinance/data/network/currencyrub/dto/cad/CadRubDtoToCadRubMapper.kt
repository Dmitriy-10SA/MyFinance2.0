package com.andef.myfinance.data.network.currencyrub.dto.cad

import com.andef.myfinance.domain.network.currencyrub.entities.CadRub

class CadRubDtoToCadRubMapper {
    fun map(cadRubDto: CadRubDto): CadRub {
        return CadRub(
            amount = cadRubDto.cadInRub.amount
        )
    }
}