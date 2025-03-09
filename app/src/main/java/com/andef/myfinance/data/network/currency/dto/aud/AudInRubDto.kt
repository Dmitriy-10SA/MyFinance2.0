package com.andef.myfinance.data.network.currency.dto.aud

import com.google.gson.annotations.SerializedName

class AudInRubDto(
    @SerializedName("rub")
    val amount: Double
)