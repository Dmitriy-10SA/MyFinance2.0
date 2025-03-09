package com.andef.myfinance.data.network.currency.dto.btc

import com.google.gson.annotations.SerializedName

class BtcRubDto(
    @SerializedName("btc")
    val btcInRub: BtcInRubDto
)