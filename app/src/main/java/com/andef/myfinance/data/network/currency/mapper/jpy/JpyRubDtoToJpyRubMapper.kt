package com.andef.myfinance.data.network.currency.mapper.jpy

import com.andef.myfinance.data.network.currency.dto.jpy.JpyRubDto
import com.andef.myfinance.domain.network.currency.entities.jpy.JpyRub
import javax.inject.Inject

class JpyRubDtoToJpyRubMapper @Inject constructor() {
    fun map(jpyRubDto: JpyRubDto): JpyRub {
        return JpyRub(
            amount = jpyRubDto.jpyInRub.amount
        )
    }
}