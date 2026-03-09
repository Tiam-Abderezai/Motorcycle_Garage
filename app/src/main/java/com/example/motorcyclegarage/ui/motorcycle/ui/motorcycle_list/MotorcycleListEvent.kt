package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list

import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

sealed class MotorcycleListEvent {
    class GetMotorcycleList : MotorcycleListEvent()

    data class SaveMotorcycleItem(
        val motorcycle: Motorcycle
    ) : MotorcycleListEvent()

    data class DeleteMotorcycleItem(
        val motorcycle: Motorcycle
    ) : MotorcycleListEvent()
}