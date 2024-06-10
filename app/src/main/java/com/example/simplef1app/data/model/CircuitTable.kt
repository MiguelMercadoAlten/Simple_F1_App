package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class CircuitTable (
    @SerializedName("Circuits"      ) var circuits      : ArrayList<Circuit> = arrayListOf()
)