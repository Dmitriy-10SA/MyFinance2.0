package com.andef.myfinance.data.network.currencyrub.dto.eth

import com.andef.myfinance.domain.network.currencyrub.entities.EthRub

class EthRubDtoToEthRubMapper {
    fun map(ethRubDto: EthRubDto): EthRub {
        return EthRub(
            amount = ethRubDto.ethInRub.amount
        )
    }
}