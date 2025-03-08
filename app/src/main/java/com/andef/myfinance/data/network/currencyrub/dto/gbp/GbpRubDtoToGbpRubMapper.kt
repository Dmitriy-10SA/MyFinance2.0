package com.andef.myfinance.data.network.currencyrub.dto.gbp

import com.andef.myfinance.domain.network.currencyrub.entities.GbpRub

class GbpRubDtoToGbpRubMapper {
    fun map(gbpRubDto: GbpRubDto): GbpRub {
        return GbpRub(
            amount = gbpRubDto.gbpInRub.amount
        )
    }
}