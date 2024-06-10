package com.example.simplef1app.data.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplef1app.data.model.Constructor

@Entity("constructor_table")
data class ConstructorEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo("id"            ) var constructorId : String,
    @ColumnInfo("url"           ) var url           : String? = null,
    @ColumnInfo("name"          ) var name          : String? = null,
    @ColumnInfo("nationality"   ) var nationality   : String? = null
) {
    companion object {
        fun fromRest (result: Constructor) = ConstructorEntity(
            constructorId = result.constructorId,
            url = result.url,
            name = result.name,
            nationality = result.nationality
        )
    }
}
