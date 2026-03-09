package com.example.motorcyclegarage.ui.components

sealed class Route(val name: String) {
    object MotorcycleListScreen : Route("MotorcycleListScreen")
    object AddMotorcycleScreen : Route("AddMotorcycleScreen")
}