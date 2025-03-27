package com.andef.myfinance.data.purpose.completed.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.myfinance.data.purpose.completed.model.CompletedPurposeModel

@Database(entities = [CompletedPurposeModel::class], version = 1, exportSchema = false)
abstract class CompletedPurposeDatabase : RoomDatabase() {
    abstract val dao: CompletedPurposeDao

    companion object {
        private const val DB_NAME = "completedPurpose.db"

        private var instance: CompletedPurposeDatabase? = null

        fun getInstance(application: Application): CompletedPurposeDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        CompletedPurposeDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}