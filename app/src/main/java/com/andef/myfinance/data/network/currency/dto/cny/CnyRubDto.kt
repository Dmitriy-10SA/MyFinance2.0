package com.andef.myfinance.data.network.currency.dto.cny

import com.google.gson.annotations.SerializedName

class CnyRubDto(
    @SerializedName("cny")
    val cnyInRub: CnyInRubDto
)