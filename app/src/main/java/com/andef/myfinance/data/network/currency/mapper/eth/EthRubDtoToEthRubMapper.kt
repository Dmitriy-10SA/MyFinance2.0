package com.andef.myfinance.data.network.currency.mapper.eth

import com.andef.myfinance.data.network.currency.dto.eth.EthRubDto
import com.andef.myfinance.domain.currency.EthRub
import javax.inject.Inject

class EthRubDtoToEthRubMapper @Inject constructor() {
    fun map(ethRubDto: EthRubDto): com.andef.myfinance.domain.currency.EthRub {
        return com.andef.myfinance.domain.currency.EthRub(
            amount = ethRubDto.ethInRub.amount
        )
    }
}