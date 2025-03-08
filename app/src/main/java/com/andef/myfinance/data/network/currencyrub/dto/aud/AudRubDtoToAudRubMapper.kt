package com.andef.myfinance.data.network.currencyrub.dto.aud

import com.andef.myfinance.domain.network.currencyrub.entities.AudRub

class AudRubDtoToAudRubMapper {
    fun map(audRubDto: AudRubDto): AudRub {
        return AudRub(
            amount = audRubDto.audInRub.amount
        )
    }
}