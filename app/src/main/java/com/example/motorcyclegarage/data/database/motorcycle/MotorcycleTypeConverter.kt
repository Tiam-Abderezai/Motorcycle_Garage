package com.example.motorcyclegarage.data.database.motorcycle

import androidx.room.TypeConverter
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Model
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TypeConverter used to convert different objects to and from Json to persist as Room database entities
object MotorcycleTypeConverter {

    private val gson = Gson()

    // --- Model ---
    @TypeConverter
    fun fromMotorcycle(motor: Motorcycle?): String? =
        motor?.let { gson.toJson(it) }

    @TypeConverter
    fun toMotorcycle(json: String?): Motorcycle? =
        json?.let { gson.fromJson(it, Motorcycle::class.java) }

    // --- Manufacturer ---
    @TypeConverter
    fun fromManufacturer(manufacturer: Manufacturer?): String? =
        manufacturer?.let { gson.toJson(it) }

    @TypeConverter
    fun toManufacturer(json: String?): Manufacturer? =
        json?.let { gson.fromJson(it, Manufacturer::class.java) }


    // --- Model List (if needed) ---
    @TypeConverter
    fun fromModelList(list: List<Model>?): String? =
        gson.toJson(list)

    @TypeConverter
    fun toModelList(json: String?): List<Model>? {
        if (json == null) return emptyList()
        val type = object : TypeToken<List<Model>>() {}.type
        return gson.fromJson(json, type)
    }

    // --- Model ---
    @TypeConverter
    fun fromModel(model: Model?): String? =
        model?.let { gson.toJson(it) }

    @TypeConverter
    fun toModel(json: String?): Model? =
        json?.let { gson.fromJson(it, Model::class.java) }
}