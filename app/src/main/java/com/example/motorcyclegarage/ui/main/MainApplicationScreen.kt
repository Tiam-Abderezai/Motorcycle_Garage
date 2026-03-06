package com.example.motorcyclegarage.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.ui.MainScreen
import com.example.motorcyclegarage.ui.motorcycle.ui.AddMotorcycleScreen
import com.example.motorcyclegarage.ui.motorcycle.ui.Route

/* The Main Screen is used to show all motorcycles the user has created.
* It also is used to go to other screens like MotorcycleScreen, and to
* add, delete, or edit motorcycle list items.
* */

var addButtonClicked by mutableStateOf(false)

@Composable
fun MainApplicationScreen() {
// Show the list of Motorcycles
    println("TIAMM MainApplicationScreen")
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.MainScreen.name) {
        composable(route = Route.MainScreen.name) {
            MainScreen(navController)
        }
        composable(route = Route.AddMotorcycleScreen.name) {
            AddMotorcycleScreen(navController)
        }
    }

}

