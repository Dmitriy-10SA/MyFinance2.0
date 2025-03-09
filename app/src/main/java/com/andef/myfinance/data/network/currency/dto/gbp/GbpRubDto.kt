package com.andef.myfinance.data.network.currency.dto.gbp

import com.google.gson.annotations.SerializedName

class GbpRubDto(
    @SerializedName("gbp")
    val gbpInRub: GbpInRubDto
)