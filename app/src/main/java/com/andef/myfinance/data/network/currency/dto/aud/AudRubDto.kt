package com.andef.myfinance.data.network.currency.dto.aud

import com.google.gson.annotations.SerializedName

class AudRubDto(
    @SerializedName("aud")
    val audInRub: AudInRubDto
)