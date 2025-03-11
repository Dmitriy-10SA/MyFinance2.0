package com.andef.myfinance.data.network.currency.mapper.btc

import com.andef.myfinance.data.network.currency.dto.btc.BtcRubDto
import com.andef.myfinance.domain.network.currency.entities.btc.BtcRub
import javax.inject.Inject

class BtcRubDtoToBtcRubMapper @Inject constructor() {
    fun map(btcRubDto: BtcRubDto): BtcRub {
        return BtcRub(
            amount = btcRubDto.btcInRub.amount
        )
    }
}