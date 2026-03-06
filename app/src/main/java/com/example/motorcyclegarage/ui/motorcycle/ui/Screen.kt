package com.example.motorcyclegarage.ui.motorcycle.ui

sealed class Route(val name: String) {
    object MainScreen : Route("MainScreen")
    object AddMotorcycleScreen : Route("AddMotorcycleScreen")
}