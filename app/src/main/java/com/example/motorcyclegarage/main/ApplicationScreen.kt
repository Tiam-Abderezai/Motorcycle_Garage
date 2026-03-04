package com.example.motorcyclegarage.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motorcyclegarage.motorcycle.Screen
import com.example.motorcyclegarage.motorcycle.crud.AddMotorcycleScreen
import com.example.motorcyclegarage.motorcycle.model.motorList
import com.example.motorcyclegarage.ui.MainScreen

/* The Main Screen is used to show all motorcycles the user has created.
* It also is used to go to other screens like MotorcycleScreen, and to
* add, delete, or edit motorcycle list items.
* */

var addButtonClicked by mutableStateOf(false)


const val USER_ID_KEY = "userId"

@Composable
fun ApplicationScreen() {
// Show the list of Motorcycles

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(motorList, navController)
        }
        composable(route = Screen.AddMotorcycleScreen.route) {
            AddMotorcycleScreen(navController)
        }
    }

}

