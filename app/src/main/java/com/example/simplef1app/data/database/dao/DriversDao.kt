package com.example.simplef1app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simplef1app.data.database.entities.DriverEntity

@Dao
interface DriversDao {
    @Query("SELECT * FROM driver_table ORDER BY given_name ASC")
    fun getAllDrivers():List<DriverEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrivers(drivers:List<DriverEntity>)

    @Query("SELECT * FROM driver_table WHERE (given_name || family_name) LIKE '%' || :name || '%' ORDER BY given_name ASC")
    fun getDriversByName(name: String): List<DriverEntity>
}