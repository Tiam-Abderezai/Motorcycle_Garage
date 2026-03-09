package com.example.motorcyclegarage.data.database.motorcycle

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Dao
interface MotorcycleListDAO {

    @Query("SELECT * FROM motorcycle_table")
    fun getAllMotorcycles(): Flow<List<Motorcycle>>

    @Query("SELECT * FROM motorcycle_table WHERE motorcycleId = :id")
    fun findMotorcycleById(id: Int): Motorcycle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMotorcycle(motorcycle: Motorcycle)

    @Update
    suspend fun updateMotorcycle(motorcycle: Motorcycle)

    @Query("DELETE FROM motorcycle_table WHERE motorcycleId = :id")
    suspend fun deleteMotorcycle(id: Int)
}