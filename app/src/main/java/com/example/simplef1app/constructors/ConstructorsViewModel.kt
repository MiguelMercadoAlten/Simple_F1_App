package com.example.simplef1app.constructors

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplef1app.api.Repository
import com.example.simplef1app.data.database.F1Database
import com.example.simplef1app.data.database.entities.ConstructorEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ConstructorsViewModel(private val repository: Repository, context: Context) : ViewModel() {
    private lateinit var  job: Job

    private val _constructors = MutableLiveData<List<ConstructorEntity>>()

    val constructors: LiveData<List<ConstructorEntity>>
        get() = _constructors

    val database = F1Database.getInstance(context)

    private val constructorsDao = database.getConstructorsDao()

    fun getConstructors(limit: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val constructorsResult = repository.getConstructors(limit, 0)
            constructorsDao.insertAllConstructors(constructorsResult.response.constructorTable.constructors.map { ConstructorEntity.fromRest(it) })
            _constructors.postValue(constructorsDao.getAllConstructors())
        }
    }

    fun getConstructorsWithOffset(limit: Int, offset: Int) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val constructorsResult = repository.getConstructors(limit, offset)
            constructorsDao.insertAllConstructors(constructorsResult.response.constructorTable.constructors.map { ConstructorEntity.fromRest(it) })
            _constructors.postValue(constructorsDao.getAllConstructors())
        }
    }

    fun getConstructorsByName(name: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _constructors.postValue(constructorsDao.getConstructorsByName(name))
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) {
            job.cancel()
        }
    }
}