package com.example.simplef1app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplef1app.data.model.Driver

@Entity("driver_table")
data class DriverEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id"        ) var driverId        : String,
    @ColumnInfo("permanent_number" ) var permanentNumber : String? = null,
    @ColumnInfo("code"            ) var code            : String? = null,
    @ColumnInfo("url"             ) var url             : String? = null,
    @ColumnInfo("given_name"       ) var givenName       : String? = null,
    @ColumnInfo("family_name"      ) var familyName      : String? = null,
    @ColumnInfo("date_of_birth"     ) var dateOfBirth     : String? = null,
    @ColumnInfo("nationality"     ) var nationality     : String? = null
): java.io.Serializable{
    companion object {
        fun fromRest (result: Driver) = DriverEntity(
            driverId = result.driverId,
            permanentNumber = result.permanentNumber,
            code = result.code,
            url = result.url,
            givenName = result.givenName,
            familyName= result.familyName,
            dateOfBirth= result.dateOfBirth,
            nationality= result.nationality,
        )
    }
}
