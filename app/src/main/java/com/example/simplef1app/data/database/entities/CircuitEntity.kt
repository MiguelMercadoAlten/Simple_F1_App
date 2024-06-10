package com.example.simplef1app.data.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplef1app.data.model.Circuit
import com.example.simplef1app.data.model.Location

@Entity("circuit_table")
data class CircuitEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo("id"            ) var circuitId   : String,
    @ColumnInfo("url"           ) var url         : String?   = null,
    @ColumnInfo("circuit_name"  ) var circuitName : String?   = null,
    @ColumnInfo("Location"      ) var location    : Location? = null
) {
    companion object {
        fun fromRest (result: Circuit) = CircuitEntity(
            circuitId = result.circuitId,
            url = result.url,
            circuitName = result.circuitName,
            location = result.location
        )
    }
}
