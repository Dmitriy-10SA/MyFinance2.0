package com.andef.myfinance.data.database.income.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.database.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.IncomeCategory

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
        newCategory: com.andef.myfinance.domain.income.entities.IncomeCategory,
        newComment: String,
        newDate: Long
    )

    @Query("DELETE FROM income WHERE id = :id")
    suspend fun removeIncome(id: Int)

    @Query("SELECT * FROM income WHERE date = :date")
    fun getIncomes(date: Long): LiveData<List<IncomeModel>>

    @Query("SELECT * FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getIncomes(startDate: Long, endDate: Long): LiveData<List<IncomeModel>>

    @Query("SELECT SUM(amount) FROM income WHERE date = :date")
    fun getFullAmount(date: Long): LiveData<Double>

    @Query("SELECT SUM(amount) FROM income WHERE date BETWEEN :startDate AND :endDate")
    fun getFullAmount(startDate: Long, endDate: Long): LiveData<Double>
}