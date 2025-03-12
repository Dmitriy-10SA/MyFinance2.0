package com.andef.myfinance.data.network.currency.dto.hkd

import com.google.gson.annotations.SerializedName

class HkdRubDto(
    @SerializedName("hkd")
    val hkdInRub: HkdInRubDto
)