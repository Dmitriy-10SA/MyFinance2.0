package com.andef.myfinance.data.reminder.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andef.myfinance.data.reminder.model.ReminderModel
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun addReminder(reminderModel: ReminderModel)

    @Query(
        """
        UPDATE reminder SET text = :text, amount = :amount, category = :category, time = :time
        WHERE id = :id
        """
    )
    suspend fun changeReminder(
        id: Int,
        text: String,
        amount: Double,
        category: ExpenseCategory,
        time: Long
    )

    @Query("DELETE FROM reminder WHERE id = :id")
    suspend fun removeReminder(id: Int)

    @Query("SELECT * FROM reminder WHERE time BETWEEN :timeStartOfDay AND :timeEndOfDay")
    fun getReminderList(timeEndOfDay: Long, timeStartOfDay: Long): Flow<List<ReminderModel>>

    @Query("SELECT * FROM reminder WHERE id = :id")
    suspend fun getReminder(id: Int): ReminderModel

    @Query("SELECT * FROM reminder")
    fun getAllReminderList(): Flow<List<ReminderModel>>

    @Query("SELECT COALESCE(MAX(id) + 1, 1) FROM reminder")
    suspend fun generateId(): Int
}