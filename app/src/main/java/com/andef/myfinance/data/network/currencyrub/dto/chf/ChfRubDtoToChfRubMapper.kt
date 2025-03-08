package com.andef.myfinance.data.network.currencyrub.dto.chf

import com.andef.myfinance.domain.network.currencyrub.entities.ChfRub

class ChfRubDtoToChfRubMapper {
    fun map(chfRubDto: ChfRubDto): ChfRub {
        return ChfRub(
            amount = chfRubDto.chfInRub.amount
        )
    }
}