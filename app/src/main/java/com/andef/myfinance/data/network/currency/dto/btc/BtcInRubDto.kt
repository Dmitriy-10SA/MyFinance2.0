package com.andef.myfinance.data.network.currency.dto.btc

import com.google.gson.annotations.SerializedName

class BtcInRubDto(
    @SerializedName("rub")
    val amount: Double
)