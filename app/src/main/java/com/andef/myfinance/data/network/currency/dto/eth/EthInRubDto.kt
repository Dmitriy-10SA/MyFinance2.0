package com.andef.myfinance.data.network.currency.dto.eth

import com.google.gson.annotations.SerializedName

class EthInRubDto(
    @SerializedName("rub")
    val amount: Double
)