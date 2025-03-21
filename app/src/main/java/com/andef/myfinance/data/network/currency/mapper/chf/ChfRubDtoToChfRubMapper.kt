package com.andef.myfinance.data.network.currency.mapper.chf

import com.andef.myfinance.data.network.currency.dto.chf.ChfRubDto
import com.andef.myfinance.domain.currency.ChfRub
import javax.inject.Inject

class ChfRubDtoToChfRubMapper @Inject constructor() {
    fun map(chfRubDto: ChfRubDto): com.andef.myfinance.domain.currency.ChfRub {
        return com.andef.myfinance.domain.currency.ChfRub(
            amount = chfRubDto.chfInRub.amount
        )
    }
}