package com.andef.myfinance.data.network.currency.dto.gbp

import com.google.gson.annotations.SerializedName

class GbpInRubDto(
    @SerializedName("rub")
    val amount: Double
)