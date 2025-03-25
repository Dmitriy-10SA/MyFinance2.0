package com.andef.myfinance.data.reminder.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.myfinance.data.reminder.model.ReminderModel

@Database(entities = [ReminderModel::class], version = 1, exportSchema = false)
abstract class ReminderDatabase : RoomDatabase() {
    abstract val dao: ReminderDao

    companion object {
        private const val DB_NAME = "reminder.db"

        private var instance: ReminderDatabase? = null

        fun getInstance(application: Application): ReminderDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        ReminderDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}