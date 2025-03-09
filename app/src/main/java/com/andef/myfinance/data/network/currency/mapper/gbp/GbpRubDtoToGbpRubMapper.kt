package com.andef.myfinance.data.network.currency.mapper.gbp

import com.andef.myfinance.data.network.currency.dto.gbp.GbpRubDto
import com.andef.myfinance.domain.network.currency.entities.gbp.GbpRub

class GbpRubDtoToGbpRubMapper {
    fun map(gbpRubDto: GbpRubDto): GbpRub {
        return GbpRub(
            amount = gbpRubDto.gbpInRub.amount
        )
    }
}