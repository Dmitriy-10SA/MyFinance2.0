package com.andef.myfinance.data.network.currency.dto.hkd

import com.google.gson.annotations.SerializedName

class HkdInRubDto(
    @SerializedName("rub")
    val amount: Double
)