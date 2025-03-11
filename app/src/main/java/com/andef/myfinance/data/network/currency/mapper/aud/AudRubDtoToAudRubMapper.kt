package com.andef.myfinance.data.network.currency.mapper.aud

import com.andef.myfinance.data.network.currency.dto.aud.AudRubDto
import com.andef.myfinance.domain.network.currency.entities.aud.AudRub
import javax.inject.Inject

class AudRubDtoToAudRubMapper @Inject constructor() {
    fun map(audRubDto: AudRubDto): AudRub {
        return AudRub(
            amount = audRubDto.audInRub.amount
        )
    }
}