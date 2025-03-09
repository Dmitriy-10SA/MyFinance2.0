package com.andef.myfinance.data.network.currency.dto.eur

import com.google.gson.annotations.SerializedName

class EurRubDto(
    @SerializedName("eur")
    val eurInRub: EurInRubDto
)