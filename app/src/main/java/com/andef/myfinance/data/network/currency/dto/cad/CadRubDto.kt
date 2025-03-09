package com.andef.myfinance.data.network.currency.dto.cad

import com.google.gson.annotations.SerializedName

class CadRubDto(
    @SerializedName("cad")
    val cadInRub: CadInRubDto
)