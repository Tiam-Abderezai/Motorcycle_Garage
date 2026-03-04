package com.example.motorcyclegarage.motorcycle.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.motorcyclegarage.database.ModelListTypeConverter
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "manufacturer_database")
data class Manufacturer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val logo: Int,
    @field:TypeConverters(ModelListTypeConverter::class)
    val models: List<Model>,
) : Parcelable

@Parcelize
@Entity(tableName = "motorcycle_database")
data class Motorcycle(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val manufacturer: Manufacturer,
    val logo: Int,
    val image: Int,
    val model: Model,
) : Parcelable

@Parcelize
@Entity(tableName = "model_database")
data class Model(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val manufacturerName: String,
    val image: Int,
    val type: String,
    val power: String,
    val year: String
) : Parcelable


