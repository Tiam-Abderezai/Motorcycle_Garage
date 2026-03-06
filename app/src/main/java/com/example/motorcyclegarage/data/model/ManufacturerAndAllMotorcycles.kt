package com.example.motorcyclegarage.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

@Embedded
var manufacturer: Manufacturer? = null

@Relation (
    parentColumn = "manufacturerId",
    entityColumn = "manufacturerId"
)

var motorcycles: List<Motorcycle> = ArrayList()