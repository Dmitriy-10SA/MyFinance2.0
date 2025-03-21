package com.andef.myfinance.data.network.currency.mapper.gbp

import com.andef.myfinance.data.network.currency.dto.gbp.GbpRubDto
import com.andef.myfinance.domain.currency.GbpRub
import javax.inject.Inject

class GbpRubDtoToGbpRubMapper @Inject constructor() {
    fun map(gbpRubDto: GbpRubDto): com.andef.myfinance.domain.currency.GbpRub {
        return com.andef.myfinance.domain.currency.GbpRub(
            amount = gbpRubDto.gbpInRub.amount
        )
    }
}