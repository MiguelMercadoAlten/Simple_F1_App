package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class Driver(
    @SerializedName("driverId"        ) var driverId        : String,
    @SerializedName("permanentNumber" ) var permanentNumber : String = "",
    @SerializedName("code"            ) var code            : String,
    @SerializedName("url"             ) var url             : String,
    @SerializedName("givenName"       ) var givenName       : String,
    @SerializedName("familyName"      ) var familyName      : String,
    @SerializedName("dateOfBirth"     ) var dateOfBirth     : String,
    @SerializedName("nationality"     ) var nationality     : String
)
