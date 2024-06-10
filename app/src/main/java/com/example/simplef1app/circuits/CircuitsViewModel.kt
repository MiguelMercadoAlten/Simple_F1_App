package com.example.simplef1app.circuits

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplef1app.api.Repository
import com.example.simplef1app.data.database.F1Database
import com.example.simplef1app.data.database.entities.CircuitEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CircuitsViewModel(private val repository: Repository, context: Context) : ViewModel() {
    private lateinit var  job: Job

    private val _circuits = MutableLiveData<List<CircuitEntity>>()
    val circuits: LiveData<List<CircuitEntity>>
        get() = _circuits

    val database = F1Database.getInstance(context)

    private val circuitsDao = database.getCircuitsDao()

    fun getCircuits(limit: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val circuitsResult = repository.getCircuits(limit, 0)
            circuitsDao.insertAllCircuits(circuitsResult.response.circuitTable.circuits.map { CircuitEntity.fromRest(it) })
            _circuits.postValue(circuitsDao.getAllCircuits())
        }
    }

    fun getCircuitsWithOffset(limit: Int, offset: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val circuitsResult = repository.getCircuits(limit, offset)
            circuitsDao.insertAllCircuits(circuitsResult.response.circuitTable.circuits.map { CircuitEntity.fromRest(it) })
            _circuits.postValue(circuitsDao.getAllCircuits())
        }
    }

    fun getCircuitsByName(name: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _circuits.postValue(circuitsDao.getCircuitsByName(name))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) {
            job.cancel()
        }
    }
}