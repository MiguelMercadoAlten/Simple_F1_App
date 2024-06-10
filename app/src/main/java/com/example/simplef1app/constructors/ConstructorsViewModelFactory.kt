package com.example.simplef1app.constructors

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplef1app.api.Repository
import com.example.simplef1app.circuits.CircuitsViewModel

class ConstructorsViewModelFactory(
    private val repository: Repository,
    private val requireContext: Context
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConstructorsViewModel(repository, requireContext) as T
    }
}