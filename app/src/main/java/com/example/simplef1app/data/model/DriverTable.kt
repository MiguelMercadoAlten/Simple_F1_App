package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class DriverTable (
    @SerializedName("Drivers" ) var drivers       : List<Driver> = listOf()
)