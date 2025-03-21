package com.andef.myfinance.data.network.currency.mapper.cny

import com.andef.myfinance.data.network.currency.dto.cny.CnyRubDto
import com.andef.myfinance.domain.currency.CnyRub
import javax.inject.Inject

class CnyRubDtoToCnyRubMapper @Inject constructor() {
    fun map(cnyRubDto: CnyRubDto): com.andef.myfinance.domain.currency.CnyRub {
        return com.andef.myfinance.domain.currency.CnyRub(
            amount = cnyRubDto.cnyInRub.amount
        )
    }
}