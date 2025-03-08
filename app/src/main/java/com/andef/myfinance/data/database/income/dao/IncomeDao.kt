package com.andef.myfinance.data.database.income.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.database.income.model.IncomeModel
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Insert
    suspend fun addIncome(income: IncomeModel)

    @Query(
        """
        UPDATE income SET amount = :newAmount, category = :newCategory,
        comment = :newComment, date = :newDate WHERE id = :id
    """
    )
    suspend fun changeIncome(
        id: Int,
        newAmount: Double,
        newCategory: IncomeCategory,
        newComment: String,
        newDate: Long
    )

    @Query("DELETE FROM income WHERE id = :id")
    suspend fun removeIncome(id: Int)

    @Query("SELECT * FROM income WHERE date = :date")
    fun getIncome(date: Long): Flow<List<IncomeModel>>

    @Query("SELECT * FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getIncome(startDate: Long, endDate: Long): Flow<List<IncomeModel>>
}