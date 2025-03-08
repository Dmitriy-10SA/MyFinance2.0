package com.andef.myfinance.data.database.income.datasource

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andef.myfinance.data.database.income.dao.IncomeDao
import com.andef.myfinance.data.database.income.model.IncomeModel

@Database(entities = [IncomeModel::class], version = 1, exportSchema = false)
abstract class IncomeDatabase : RoomDatabase() {
    abstract val dao: IncomeDao

    companion object {
        private const val DB_NAME = "income.db"

        private var instance: IncomeDatabase? = null

        fun getInstance(application: Application): IncomeDatabase {
            if (instance != null) {
                return instance!!
            }
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        application,
                        IncomeDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance!!
            }
        }
    }
}