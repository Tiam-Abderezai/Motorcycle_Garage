package com.example.motorcyclegarage.data.database.motorcycle

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle


@Dao
interface MotorcycleDAO {

    @Query("SELECT * FROM motorcycle_table")
    fun getAllMotorcycles(): List<Motorcycle>

    @Query("SELECT * FROM motorcycle_table WHERE motorcycleId = :id")
    fun findMotorcycleById(id: Int): Motorcycle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMotorcycle(motorcycle: Motorcycle)

    @Update
    suspend fun updateMotorcycle(motorcycle: Motorcycle)

    @Delete
    suspend fun deleteMotorcycle(motorcycle: Motorcycle)
}