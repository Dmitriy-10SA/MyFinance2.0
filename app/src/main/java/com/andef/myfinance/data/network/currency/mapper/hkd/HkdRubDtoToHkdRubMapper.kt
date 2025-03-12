package com.andef.myfinance.data.network.currency.mapper.hkd

import com.andef.myfinance.data.network.currency.dto.hkd.HkdRubDto
import com.andef.myfinance.domain.network.currency.entities.hkd.HkdRub
import javax.inject.Inject

class HkdRubDtoToHkdRubMapper @Inject constructor() {
    fun map(hkdRubDto: HkdRubDto): HkdRub {
        return HkdRub(
            amount = hkdRubDto.hkdInRub.amount
        )
    }
}