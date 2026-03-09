package com.example.motorcyclegarage.data.database.motorcycle

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

@Database(entities = [Motorcycle::class], version = 1, exportSchema = false)
@TypeConverters(MotorcycleTypeConverter::class)
abstract class MotorcycleListDatabase : RoomDatabase() {
    abstract fun motorcycleListDAO(): MotorcycleListDAO
}