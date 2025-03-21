package com.andef.myfinance.data.network.currency.mapper.btc

import com.andef.myfinance.data.network.currency.dto.btc.BtcRubDto
import com.andef.myfinance.domain.currency.BtcRub
import javax.inject.Inject

class BtcRubDtoToBtcRubMapper @Inject constructor() {
    fun map(btcRubDto: BtcRubDto): com.andef.myfinance.domain.currency.BtcRub {
        return com.andef.myfinance.domain.currency.BtcRub(
            amount = btcRubDto.btcInRub.amount
        )
    }
}