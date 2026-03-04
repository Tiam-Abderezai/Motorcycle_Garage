package com.example.motorcyclegarage.database

import androidx.room.TypeConverter
import com.example.motorcyclegarage.motorcycle.model.Manufacturer
import com.example.motorcyclegarage.motorcycle.model.Model
import com.example.motorcyclegarage.motorcycle.model.Motorcycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ModelListTypeConverter {

    private val gson = Gson()

    // --- Model ---
    @TypeConverter
    fun fromModel(model: Model?): String? =
        model?.let { gson.toJson(it) }

    @TypeConverter
    fun toModel(json: String?): Model? =
        json?.let { gson.fromJson(it, Model::class.java) }

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
}