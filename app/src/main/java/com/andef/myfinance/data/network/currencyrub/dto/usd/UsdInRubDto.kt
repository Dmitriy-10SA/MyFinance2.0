package com.andef.myfinance.data.network.currencyrub.dto.usd

import com.google.gson.annotations.SerializedName

class UsdInRubDto(
    @SerializedName("rub")
    val amount: Double
)