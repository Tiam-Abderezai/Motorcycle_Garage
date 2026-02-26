package com.example.motorcyclegarage.motorcycle

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object AddMotorcycleScreen : Screen("AddMotorcycleScreen")
}