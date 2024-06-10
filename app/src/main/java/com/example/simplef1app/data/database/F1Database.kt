package com.example.simplef1app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simplef1app.data.database.dao.CircuitsDao
import com.example.simplef1app.data.database.dao.ConstructorsDao
import com.example.simplef1app.data.database.dao.DriversDao
import com.example.simplef1app.data.database.entities.CircuitEntity
import com.example.simplef1app.data.database.entities.ConstructorEntity
import com.example.simplef1app.data.database.entities.DriverEntity

@Database(entities = [DriverEntity::class, CircuitEntity::class, ConstructorEntity::class], version = 8)
@TypeConverters(com.example.simplef1app.api.TypeConverters::class)
abstract class F1Database: RoomDatabase() {
    abstract fun getDriversDao(): DriversDao
    abstract fun getConstructorsDao(): ConstructorsDao
    abstract fun getCircuitsDao(): CircuitsDao

    companion object {
        private var INSTANCE: F1Database? = null

        fun getInstance(context: Context): F1Database {
            if (INSTANCE == null) {
                synchronized(F1Database::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        F1Database::class.java, "user.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
