package com.andef.myfinance.data.income.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.IncomeCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Insert
    suspend fun addIncome(income: IncomeModel)

    @Query(
        """
        UPDATE income SET amount = :amount, category = :category, comment = :comment, date = :date
        WHERE id = :id
    """
    )
    suspend fun changeIncome(
        id: Int,
        amount: Double,
        category: IncomeCategory,
        comment: String,
        date: Long
    )

    @Query("DELETE FROM income WHERE id = :id")
    suspend fun removeIncome(id: Int)

    @Query("SELECT * FROM income WHERE id = :id")
    suspend fun getIncome(id: Int): IncomeModel

    @Query("SELECT * FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getIncomeList(startDate: Long, endDate: Long): Flow<List<IncomeModel>>

    @Query("SELECT SUM(amount) FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getFullIncomeAmount(startDate: Long, endDate: Long): Flow<Double>
}