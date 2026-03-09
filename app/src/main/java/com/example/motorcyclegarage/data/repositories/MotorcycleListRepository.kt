package com.example.motorcyclegarage.data.repositories

import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDAO
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

//import com.example.motorcyclegarage.motorcycle.motorcycleList

interface MotorcycleListRepository {
    val allMotorcycles: Flow<List<Motorcycle>>
    suspend fun insertMotorcycle(motorcycle: Motorcycle)
    suspend fun deleteMotorcycle(motorcycleId: Int)
}