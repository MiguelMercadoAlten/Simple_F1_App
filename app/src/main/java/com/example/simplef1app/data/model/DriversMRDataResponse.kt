package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class DriversMRDataResponse(
    @SerializedName("MRData") var response: DriversMRData = DriversMRData()
)
