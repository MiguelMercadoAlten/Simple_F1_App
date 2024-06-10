package com.example.simplef1app.api

import com.example.simplef1app.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface F1Api {
    @GET("drivers.json")
    suspend fun getDrivers(@Query("limit") limit: String, @Query("offset") offset: String) : Response<DriversMRDataResponse>

    @GET("circuits.json")
    suspend fun getCircuits(@Query("limit") limit: String, @Query("offset") offset: String) : Response<CircuitsMRDataResponse>

    @GET("constructors.json")
    suspend fun getConstructors(@Query("limit") limit: String, @Query("offset") offset: String) : Response<ConstructorsMRDataResponse>

    companion object {
        operator fun invoke(): F1Api = Retrofit.getRetrofit(
            baseURL = "https://ergast.com/api/f1/",
            F1Api::class.java
        ) as F1Api
    }
}