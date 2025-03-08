package com.andef.myfinance.data.network.currencyrub.dto.btc

import com.google.gson.annotations.SerializedName

class BtcInRubDto(
    @SerializedName("rub")
    val amount: Double
)