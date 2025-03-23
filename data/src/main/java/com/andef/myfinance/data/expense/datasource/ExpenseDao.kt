package com.andef.myfinance.data.expense.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.expense.model.ExpenseModel
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun addExpense(expenseModel: ExpenseModel)

    @Query(
        """
        UPDATE expense SET amount = :amount, category = :category, comment = :comment, date = :date
        WHERE id = :id
        """
    )
    suspend fun changeExpense(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Long
    )

    @Query("DELETE FROM expense WHERE id = :id")
    suspend fun removeExpense(id: Int)

    @Query("SELECT * FROM expense WHERE id = :id")
    suspend fun getExpense(id: Int): ExpenseModel

    @Query("SELECT * FROM expense WHERE date BETWEEN :startDate AND :endDate")
    fun getExpenseList(startDate: Long, endDate: Long): Flow<List<ExpenseModel>>

    @Query("SELECT SUM(amount) FROM expense WHERE date BETWEEN :startDate AND :endDate")
    fun getFullExpenseAmount(startDate: Long, endDate: Long): Flow<Double>
}