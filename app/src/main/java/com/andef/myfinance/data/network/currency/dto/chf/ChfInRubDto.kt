package com.andef.myfinance.data.network.currency.dto.chf

import com.google.gson.annotations.SerializedName

class ChfInRubDto(
    @SerializedName("rub")
    val amount: Double
)