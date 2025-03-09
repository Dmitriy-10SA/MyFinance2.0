package com.andef.myfinance.data.network.currency.dto.jpy

import com.google.gson.annotations.SerializedName

class JpyInRubDto(
    @SerializedName("rub")
    val amount: Double
)