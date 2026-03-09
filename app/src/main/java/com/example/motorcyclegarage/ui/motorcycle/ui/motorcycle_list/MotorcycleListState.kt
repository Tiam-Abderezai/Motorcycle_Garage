package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list

import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

sealed class MotorcycleListState {
    object Initial : MotorcycleListState()
    object Loading : MotorcycleListState()
    object Empty : MotorcycleListState()
    data class Complete(
        val motorcycleList: List<Motorcycle>
    ) : MotorcycleListState()
    data class Error(val message: String) : MotorcycleListState()
}