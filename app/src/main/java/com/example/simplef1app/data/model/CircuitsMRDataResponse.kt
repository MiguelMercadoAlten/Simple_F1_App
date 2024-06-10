package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class CircuitsMRDataResponse(
    @SerializedName("MRData") var response: CircuitsMRData = CircuitsMRData()
)
