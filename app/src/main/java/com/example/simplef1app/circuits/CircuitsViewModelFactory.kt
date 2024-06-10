package com.example.simplef1app.circuits

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplef1app.api.Repository

class CircuitsViewModelFactory(
    private val repository: Repository,
    private val requireContext: Context
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CircuitsViewModel(repository, requireContext) as T
    }
}