package com.andef.myfinance.data.purpose.active.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activePurpose")
data class ActivePurposeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val targetSum: Double,
    val currentSum: Double,
    val photoUri: String
)
