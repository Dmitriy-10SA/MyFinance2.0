package com.andef.myfinance.data.network.currency.dto.eth

import com.google.gson.annotations.SerializedName

class EthRubDto(
    @SerializedName("eth")
    val ethInRub: EthInRubDto
)