package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class ConstructorsMRDataResponse(
    @SerializedName("MRData") var response: ConstructorsMRData = ConstructorsMRData()
)
