package com.andef.myfinance.data.network.currency.mapper.eth

import com.andef.myfinance.data.network.currency.dto.eth.EthRubDto
import com.andef.myfinance.domain.network.currency.entities.eth.EthRub

class EthRubDtoToEthRubMapper {
    fun map(ethRubDto: EthRubDto): EthRub {
        return EthRub(
            amount = ethRubDto.ethInRub.amount
        )
    }
}