package com.andef.myfinance.data.network.currency.mapper.jpy

import com.andef.myfinance.data.network.currency.dto.jpy.JpyRubDto
import com.andef.myfinance.domain.currency.JpyRub
import javax.inject.Inject

class JpyRubDtoToJpyRubMapper @Inject constructor() {
    fun map(jpyRubDto: JpyRubDto): com.andef.myfinance.domain.currency.JpyRub {
        return com.andef.myfinance.domain.currency.JpyRub(
            amount = jpyRubDto.jpyInRub.amount
        )
    }
}