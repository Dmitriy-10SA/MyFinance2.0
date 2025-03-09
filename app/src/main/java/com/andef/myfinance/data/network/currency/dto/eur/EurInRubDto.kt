package com.andef.myfinance.data.network.currency.dto.eur

import com.google.gson.annotations.SerializedName

class EurInRubDto(
    @SerializedName("rub")
    val amount: Double
)