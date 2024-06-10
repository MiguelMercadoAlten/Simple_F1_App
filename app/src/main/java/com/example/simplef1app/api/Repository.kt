package com.example.simplef1app.api

class Repository (private val apiF1: F1Api) : SafeApiRequest() {
    suspend fun getDrivers(limit: Int, offset: Int) = apiRequest { apiF1.getDrivers("$limit", "$offset") }
    suspend fun getCircuits(limit: Int, offset: Int) = apiRequest { apiF1.getCircuits("$limit", "$offset") }
    suspend fun getConstructors(limit: Int, offset: Int) = apiRequest { apiF1.getConstructors("$limit", "$offset") }
}