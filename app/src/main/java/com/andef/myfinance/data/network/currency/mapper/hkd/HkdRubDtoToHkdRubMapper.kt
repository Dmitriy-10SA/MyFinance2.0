package com.andef.myfinance.data.network.currency.mapper.hkd

import com.andef.myfinance.data.network.currency.dto.hkd.HkdRubDto
import com.andef.myfinance.domain.currency.HkdRub
import javax.inject.Inject

class HkdRubDtoToHkdRubMapper @Inject constructor() {
    fun map(hkdRubDto: HkdRubDto): com.andef.myfinance.domain.currency.HkdRub {
        return com.andef.myfinance.domain.currency.HkdRub(
            amount = hkdRubDto.hkdInRub.amount
        )
    }
}