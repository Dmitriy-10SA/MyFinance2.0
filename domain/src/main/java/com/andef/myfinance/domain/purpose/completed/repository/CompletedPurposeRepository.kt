package com.andef.myfinance.domain.purpose.completed.repository

import com.andef.myfinance.domain.purpose.completed.entities.CompletedPurpose
import kotlinx.coroutines.flow.Flow

interface CompletedPurposeRepository {
    fun getCompletedPurposeList(): Flow<List<CompletedPurpose>>
    suspend fun removeCompletedPurpose(id: Int)
    suspend fun addCompletedPurpose(completedPurpose: CompletedPurpose)
}