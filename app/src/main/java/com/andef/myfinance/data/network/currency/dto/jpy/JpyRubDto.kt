package com.andef.myfinance.data.network.currency.dto.jpy

import com.google.gson.annotations.SerializedName

class JpyRubDto(
    @SerializedName("jpy")
    val jpyInRub: JpyInRubDto
)