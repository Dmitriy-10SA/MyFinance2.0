package com.andef.myfinance.data.purpose.completed.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completedPurpose")
data class CompletedPurposeModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val sum: Double,
    val photoUri: String
)
