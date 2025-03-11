package com.andef.myfinance.data.network.currency.mapper.gbp

import com.andef.myfinance.data.network.currency.dto.gbp.GbpRubDto
import com.andef.myfinance.domain.network.currency.entities.gbp.GbpRub
import javax.inject.Inject

class GbpRubDtoToGbpRubMapper @Inject constructor() {
    fun map(gbpRubDto: GbpRubDto): GbpRub {
        return GbpRub(
            amount = gbpRubDto.gbpInRub.amount
        )
    }
}