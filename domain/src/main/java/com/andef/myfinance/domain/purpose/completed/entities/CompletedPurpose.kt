package com.andef.myfinance.domain.purpose.completed.entities

data class CompletedPurpose(
    val id: Int = 0,
    val name: String,
    val sum: Double,
    val photoUri: String
)
