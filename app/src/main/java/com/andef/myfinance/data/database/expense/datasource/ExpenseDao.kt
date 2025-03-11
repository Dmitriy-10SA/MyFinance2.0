package com.andef.myfinance.data.database.expense.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.database.expense.model.ExpenseModel
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun addExpense(expenseModel: ExpenseModel)

    @Query(
        """
        UPDATE expense SET amount = :newAmount, category = :newCategory, 
        comment = :newComment, date = :newDate WHERE id = :id
        """
    )
    suspend fun changeExpense(
        id: Int,
        newAmount: Double,
        newCategory: ExpenseCategory,
        newComment: String,
        newDate: Long
    )

    @Query("DELETE FROM expense WHERE id = :id")
    suspend fun removeExpense(id: Int)

    @Query("SELECT * FROM expense WHERE date = :date")
    fun getExpense(date: Long): LiveData<List<ExpenseModel>>

    @Query("SELECT * FROM expense WHERE date BETWEEN :startDate AND :endDate")
    fun getExpense(startDate: Long, endDate: Long): LiveData<List<ExpenseModel>>

    @Query("SELECT SUM(amount) FROM expense WHERE date BETWEEN :startDate AND :endDate")
    fun getFullAmount(startDate: Long, endDate: Long): LiveData<Double>
}