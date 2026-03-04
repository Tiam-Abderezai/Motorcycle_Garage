package com.example.motorcyclegarage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.motorcyclegarage.motorcycle.model.Motorcycle
import com.example.motorcyclegarage.motorcycle.model.MotorcycleDAO

@Database(entities = [Motorcycle::class], version = 1, exportSchema = false)
@TypeConverters(ModelListTypeConverter::class)
abstract class MotorcycleDatabase : RoomDatabase() {
    abstract fun getMotorcycleDAO(): MotorcycleDAO
}