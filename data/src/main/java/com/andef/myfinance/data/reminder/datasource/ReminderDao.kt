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
        UPDATE reminder SET text = :text AND amount = :amount AND category = :category AND time = :time
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

    @Query("SELECT * FROM reminder WHERE time BETWEEN :startDate AND :endDate")
    fun getReminderList(startDate: Long, endDate: Long): Flow<List<ReminderModel>>
}