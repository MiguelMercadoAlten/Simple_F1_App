package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class Circuit(
    @SerializedName("circuitId"   ) var circuitId   : String,
    @SerializedName("url"         ) var url         : String?   = null,
    @SerializedName("circuitName" ) var circuitName : String?   = null,
    @SerializedName("Location"    ) var location    : Location? = null
)
