package com.andef.myfinance.data.purpose.active.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.purpose.active.model.ActivePurposeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivePurposeDao {
    @Insert
    suspend fun addActivePurpose(activePurposeModel: ActivePurposeModel)

    @Query(
        """
        UPDATE activePurpose
        SET name = :name, targetSum = :targetSum, currentSum = :currentSum, photoUri = :photoUri
        WHERE id = :id
        """
    )
    suspend fun changeActivePurpose(
        id: Int,
        name: String,
        targetSum: Double,
        currentSum: Double,
        photoUri: String
    )

    @Query("DELETE FROM activePurpose WHERE id = :id")
    suspend fun removeActivePurpose(id: Int)

    @Query("UPDATE activePurpose SET currentSum = currentSum + :amount WHERE id = :id")
    suspend fun addToSum(id: Int, amount: Double)

    @Query("UPDATE activePurpose SET currentSum = currentSum - :amount WHERE id = :id")
    suspend fun removeFromSum(id: Int, amount: Double)

    @Query("SELECT * FROM activePurpose")
    fun getActivePurposeList(): Flow<List<ActivePurposeModel>>
}