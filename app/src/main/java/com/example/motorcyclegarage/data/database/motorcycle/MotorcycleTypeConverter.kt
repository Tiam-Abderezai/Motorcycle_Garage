package com.example.motorcyclegarage.data.database.motorcycle

import androidx.room.TypeConverter
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.google.gson.Gson
import org.joda.time.LocalDate
import java.util.Date

// TypeConverter used to convert different objects to and from Json to persist as Room database entities
object MotorcycleTypeConverter {

    private val gson = Gson()

    // --- Manufacturer ---
    @TypeConverter
    fun fromManufacturer(manufacturer: Manufacturer?): String? =
        manufacturer?.let { gson.toJson(it) }

    @TypeConverter
    fun toManufacturer(json: String?): Manufacturer? =
        json?.let { gson.fromJson(it, Manufacturer::class.java) }

    // --- Model ---
    @TypeConverter
    fun fromModel(model: Model?): String? =
        model?.let { gson.toJson(it) }

    @TypeConverter
    fun toModel(json: String?): Model? =
        json?.let { gson.fromJson(it, Model::class.java) }
}