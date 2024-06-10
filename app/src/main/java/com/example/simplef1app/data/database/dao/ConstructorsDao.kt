package com.example.simplef1app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simplef1app.data.database.entities.ConstructorEntity
import com.example.simplef1app.data.database.entities.DriverEntity

@Dao
interface ConstructorsDao {
    @Query("select * from constructor_table order by name asc")
    fun getAllConstructors(): List<ConstructorEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllConstructors(drivers:List<ConstructorEntity>)

    @Query("SELECT * FROM constructor_table WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun getConstructorsByName(name: String): List<ConstructorEntity>
}