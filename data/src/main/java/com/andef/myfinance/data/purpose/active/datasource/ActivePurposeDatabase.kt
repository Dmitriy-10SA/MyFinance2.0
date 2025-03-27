package com.andef.myfinance.data.purpose.active.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.myfinance.data.purpose.active.model.ActivePurposeModel

@Database(entities = [ActivePurposeModel::class], version = 1, exportSchema = false)
abstract class ActivePurposeDatabase : RoomDatabase() {
    abstract val dao: ActivePurposeDao

    companion object {
        private const val DB_NAME = "activePurpose.db"

        private var instance: ActivePurposeDatabase? = null

        fun getInstance(application: Application): ActivePurposeDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        ActivePurposeDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}