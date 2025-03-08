package com.andef.myfinance.data.network.currencyrub.dto.jpy

import com.andef.myfinance.domain.network.currencyrub.entities.JpyRub

class JpyRubDtoToJpyRubMapper {
    fun map(jpyRubDto: JpyRubDto): JpyRub {
        return JpyRub(
            amount = jpyRubDto.jpyInRub.amount
        )
    }
}