package com.andef.myfinance.data.network.currency.dto.usd

import com.google.gson.annotations.SerializedName

class UsdInRubDto(
    @SerializedName("rub")
    val amount: Double
)