package com.example.simplef1app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simplef1app.data.database.entities.CircuitEntity

@Dao
interface CircuitsDao {
    @Query("select * from circuit_table order by circuit_name asc")
    fun getAllCircuits(): List<CircuitEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllCircuits(drivers:List<CircuitEntity>)

    @Query("SELECT * FROM circuit_table WHERE circuit_name LIKE '%' || :name || '%' ORDER BY circuit_name ASC")
    fun getCircuitsByName(name: String): List<CircuitEntity>
}