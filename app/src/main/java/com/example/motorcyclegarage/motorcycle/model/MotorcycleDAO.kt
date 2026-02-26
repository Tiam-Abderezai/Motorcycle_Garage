package com.example.motorcyclegarage.motorcycle.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


//@Dao
//interface MotorcycleDAO {
//
//    @Query("SELECT * FROM motorcycles")
//    fun getAllMotorcycles(): List<Motorcycle>
//
//    @Query("SELECT * FROM motorcycles WHERE motorcycleId = :crId")
//    fun findMotorcycleById(id: Int): Motorcycle
//
//    @Insert
//    suspend fun addMotorcycle(motorcycle: Motorcycle)
//
//    @Update
//    suspend fun updateMotorcycle(motorcycle: Motorcycle)
//
//    @Delete
//    suspend fun deleteMotorcycle(motorcycle: Motorcycle)
//}