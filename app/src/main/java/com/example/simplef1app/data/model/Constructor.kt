package com.example.simplef1app.data.model

import com.google.gson.annotations.SerializedName

data class Constructor(
    @SerializedName("constructorId" ) var constructorId : String,
    @SerializedName("url"           ) var url           : String? = null,
    @SerializedName("name"          ) var name          : String? = null,
    @SerializedName("nationality"   ) var nationality   : String? = null
)
