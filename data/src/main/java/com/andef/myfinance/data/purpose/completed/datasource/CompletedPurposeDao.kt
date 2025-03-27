package com.andef.myfinance.data.purpose.completed.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.purpose.completed.model.CompletedPurposeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedPurposeDao {
    @Query("SELECT * FROM completedPurpose")
    fun getCompletedPurposeList(): Flow<List<CompletedPurposeModel>>

    @Query("DELETE FROM completedPurpose WHERE id = :id")
    suspend fun removeCompletedPurpose(id: Int)

    @Insert
    suspend fun addCompletedPurpose(completedPurposeModel: CompletedPurposeModel)
}