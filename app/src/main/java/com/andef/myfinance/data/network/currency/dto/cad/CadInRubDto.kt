package com.andef.myfinance.data.network.currency.dto.cad

import com.google.gson.annotations.SerializedName

class CadInRubDto(
    @SerializedName("rub")
    val amount: Double
)