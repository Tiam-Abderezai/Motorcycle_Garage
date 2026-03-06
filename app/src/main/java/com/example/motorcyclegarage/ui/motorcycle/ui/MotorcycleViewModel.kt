package com.example.motorcyclegarage.ui.motorcycle.ui

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleRepositoryImpl
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import kotlinx.coroutines.launch

class MotorcycleViewModel(
    private val repository: MotorcycleRepositoryImpl
) : ViewModel() {

    private val isError: MutableState<Boolean> = mutableStateOf(false)
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    private val _motorcycleListResponse: MutableLiveData<List<Motorcycle>?> = MutableLiveData()
    val motorcycleListResponse: LiveData<List<Motorcycle>?> = _motorcycleListResponse
    //
    init {
        println("TIAMMM MotorcycleViewModel")
        addMotorcycle()
    }

    @VisibleForTesting
    fun addMotorcycle() {
        viewModelScope.launch {
            println("TIAMMM MotorcycleViewModel addMotorcycle()")
//            _motorcycleListResponse.value =
        }

    }
}