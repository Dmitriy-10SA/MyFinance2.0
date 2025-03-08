package com.andef.myfinance.data.network.currencyrub.dto.usd

import com.google.gson.annotations.SerializedName

class UsdRubDto(
    @SerializedName("usd")
    val usdInRub: UsdInRubDto
)