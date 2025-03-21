package com.andef.myfinance.data.network.currency.mapper.aud

import com.andef.myfinance.data.network.currency.dto.aud.AudRubDto
import com.andef.myfinance.domain.currency.AudRub
import javax.inject.Inject

class AudRubDtoToAudRubMapper @Inject constructor() {
    fun map(audRubDto: AudRubDto): com.andef.myfinance.domain.currency.AudRub {
        return com.andef.myfinance.domain.currency.AudRub(
            amount = audRubDto.audInRub.amount
        )
    }
}