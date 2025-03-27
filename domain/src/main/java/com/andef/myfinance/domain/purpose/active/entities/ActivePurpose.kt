package com.andef.myfinance.domain.purpose.active.entities

data class ActivePurpose(
    val id: Int = 0,
    val name: String,
    val targetSum: Double,
    val currentSum: Double,
    val photoUri: String
)
