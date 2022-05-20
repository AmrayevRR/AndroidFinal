package com.example.navigationcomponent.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FirstViewModel::class.java)){
            return FirstViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}