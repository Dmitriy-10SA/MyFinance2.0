package com.andef.myfinance.domain.purpose.active.repository

import com.andef.myfinance.domain.purpose.active.entities.ActivePurpose
import kotlinx.coroutines.flow.Flow

interface ActivePurposeRepository {
    suspend fun addActivePurpose(activePurpose: ActivePurpose)
    suspend fun changeActivePurpose(
        id: Int,
        name: String,
        targetSum: Double,
        currentSum: Double,
        photoUri: String
    )

    suspend fun addActivePurposeToCompletedPurpose(activePurpose: ActivePurpose)
    suspend fun removeActivePurpose(id: Int)
    suspend fun addToSum(id: Int, amount: Double)
    suspend fun removeFromSum(id: Int, amount: Double)
    fun getActivePurposeList(): Flow<List<ActivePurpose>>
}