package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class ConstructorsMRData(
    @SerializedName("xmlns"            ) var xmlns            : String?           = null,
    @SerializedName("series"           ) var series           : String?           = null,
    @SerializedName("url"            ) var url            : String?           = null,
    @SerializedName("limit"            ) var limit            : String?           = null,
    @SerializedName("offset"           ) var offset           : String?           = null,
    @SerializedName("total"            ) var total            : String?           = null,
    @SerializedName("ConstructorTable" ) var constructorTable : ConstructorTable = ConstructorTable()
)
