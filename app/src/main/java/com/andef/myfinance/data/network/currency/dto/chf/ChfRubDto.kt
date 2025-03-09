package com.andef.myfinance.data.network.currency.dto.chf

import com.google.gson.annotations.SerializedName

class ChfRubDto(
    @SerializedName("chf")
    val chfInRub: ChfInRubDto
)