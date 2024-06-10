package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class CircuitsMRData(
    @SerializedName("xmlns"       ) var xmlns       : String?      = null,
    @SerializedName("series"      ) var series      : String?      = null,
    @SerializedName("limit"       ) var limit       : String?      = null,
    @SerializedName("offset"      ) var offset      : String?      = null,
    @SerializedName("total"       ) var total       : String?      = null,
    @SerializedName("CircuitTable") var circuitTable : CircuitTable = CircuitTable()
)

