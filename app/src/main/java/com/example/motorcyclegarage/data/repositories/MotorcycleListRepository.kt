package com.example.motorcyclegarage.data.repositories

import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import kotlinx.coroutines.flow.Flow

interface MotorcycleListRepository {
    val allMotorcycles: Flow<List<Motorcycle>>
    suspend fun insertMotorcycle(motorcycle: Motorcycle)
    suspend fun deleteMotorcycle(motorcycleId: Int)
}