package com.example.simplef1app.api

import androidx.room.TypeConverter
import com.example.simplef1app.data.model.Location

class TypeConverters {
    @TypeConverter
    fun storedStringToLocation(locString: String?): Location? =
         if (locString != null) {
            val stringList = locString.split("~!!!!!~")
            Location(stringList[0], stringList[1], stringList[2], stringList[3])
        } else {
            null
        }

    @TypeConverter
    fun locationToStoredString(loc: Location?): String? =
        if (loc !=null) {
            arrayOf(
                loc.lat,
                loc.long,
                loc.locality,
                loc.country
            ).joinToString(separator = "~!!!!!~")
        } else {
            null
        }
}