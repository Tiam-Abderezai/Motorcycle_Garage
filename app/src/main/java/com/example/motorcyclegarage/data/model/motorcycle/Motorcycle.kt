package com.example.motorcyclegarage.data.model.motorcycle

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleTypeConverter
import kotlinx.parcelize.Parcelize
import java.time.LocalDate


@Parcelize
@Entity(tableName = "motorcycle_table")
data class Motorcycle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "motorcycleId")
    val id: Int = 0,
    @field:TypeConverters(MotorcycleTypeConverter::class)
    @ColumnInfo(name = "motorManufacturer")
    val manufacturer: Manufacturer,
    @ColumnInfo(name = "motorcycleLogo")
    val logo: Int,
    @ColumnInfo(name = "motorcycleImage")
    val image: Int? = null,
    @field:TypeConverters(MotorcycleTypeConverter::class)
    @ColumnInfo(name = "motorcycleModel")
    val model: Model? = null,
) : Parcelable

@Parcelize
@Entity(tableName = "manufacturer_table")
data class Manufacturer(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "manufacturerId")
    val id: Int,
    @ColumnInfo(name = "manufacturerName")
    val name: String,
    @ColumnInfo(name = "manufacturerLogo")
    val logo: Int,
    @field:TypeConverters(MotorcycleTypeConverter::class)
    @ColumnInfo(name = "manufacturerModelList")
    val models: List<Model>? = null,
) : Parcelable

@Parcelize
@Entity(tableName = "model_table")
data class Model(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "modelId")
    val id: Int,
    @ColumnInfo(name = "modelName")
    val name: String,
    @ColumnInfo(name = "manufacturerId")
    val manufacturerId: Int,
    @ColumnInfo(name = "modelImage")
    val image: Int,
    @ColumnInfo(name = "modelType")
    val type: String,
    @ColumnInfo(name = "modelPower")
    val power: String,
    @ColumnInfo(name = "modelDateCreated")
    val dateCreated: LocalDate
) : Parcelable


