package com.andef.myfinance.data.network.currency.mapper.chf

import com.andef.myfinance.data.network.currency.dto.chf.ChfRubDto
import com.andef.myfinance.domain.network.currency.entities.chf.ChfRub

class ChfRubDtoToChfRubMapper {
    fun map(chfRubDto: ChfRubDto): ChfRub {
        return ChfRub(
            amount = chfRubDto.chfInRub.amount
        )
    }
}