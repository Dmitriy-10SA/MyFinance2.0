package com.andef.myfinance.data.network.currency.dto.cny

import com.google.gson.annotations.SerializedName

class CnyInRubDto(
    @SerializedName("rub")
    val amount: Double
)