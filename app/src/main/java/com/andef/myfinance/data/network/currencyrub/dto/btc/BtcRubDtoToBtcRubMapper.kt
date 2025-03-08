package com.andef.myfinance.data.network.currencyrub.dto.btc

import com.andef.myfinance.domain.network.currencyrub.entities.BtcRub

class BtcRubDtoToBtcRubMapper {
    fun map(btcRubDto: BtcRubDto): BtcRub {
        return BtcRub(
            amount = btcRubDto.btcInRub.amount
        )
    }
}