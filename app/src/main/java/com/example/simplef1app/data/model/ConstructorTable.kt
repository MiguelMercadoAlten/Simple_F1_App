package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class ConstructorTable(
    @SerializedName("Constructors" ) var constructors : ArrayList<Constructor> = arrayListOf()
)
