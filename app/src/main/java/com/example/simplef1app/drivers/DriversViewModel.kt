package com.example.simplef1app.drivers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplef1app.api.Repository
import com.example.simplef1app.data.database.F1Database
import com.example.simplef1app.data.database.entities.DriverEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class DriversViewModel(private val repository: Repository, context: Context) : ViewModel() {
    private lateinit var  job: Job

    private val _drivers =  MutableLiveData<List<DriverEntity>>()
    val drivers: LiveData<List<DriverEntity>>
        get() = _drivers

    val database = F1Database.getInstance(context)

    private val driversDao = database.getDriversDao()

    fun getDrivers(limit: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val driversResult = repository.getDrivers(limit, 0)
            driversDao.insertAllDrivers(driversResult.response.driverTable.drivers.map { DriverEntity.fromRest(it) })
            _drivers.postValue(driversDao.getAllDrivers())
        }
    }

    fun getDriversWithOffset(limit: Int, offset: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val driversResult = repository.getDrivers(limit, offset)
            driversDao.insertAllDrivers(driversResult.response.driverTable.drivers.map { DriverEntity.fromRest(it) })
            _drivers.postValue(driversDao.getAllDrivers())
        }
    }

    fun getDriversByName(name: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _drivers.postValue(driversDao.getDriversByName(name))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) {
            job.cancel()
        }
    }
}