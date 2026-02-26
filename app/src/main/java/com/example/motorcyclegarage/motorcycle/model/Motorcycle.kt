package com.example.motorcyclegarage.motorcycle.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "manufacturer")
data class Manufacturer(
    val id: Int,
    val name: String,
    val logo: Int,
    val models: List<Model>,
) : Parcelable

@Parcelize
@Entity(tableName = "motorcycle")
data class Motorcycle(
    val id: Int,
    val manufacturer: Manufacturer,
    val logo: Int,
    val image: Int,
    val model: Model,
) : Parcelable

@Parcelize
@Entity(tableName = "model")
data class Model(
    val id: Int,
    val name: String,
    val manufacturerName: String,
    val image: Int,
    val type: String,
    val power: String,
    val year: String
) : Parcelable


