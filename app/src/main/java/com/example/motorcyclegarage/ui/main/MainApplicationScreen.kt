package com.example.motorcyclegarage.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.ui.components.Route
import com.example.motorcyclegarage.ui.motorcycle.ui.add_motorcycle.AddMotorcycleScreen
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListScreen
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState

/* MainApplicationScreen() is the entry Composable called by the MainActivity that hosts 2 main
Composables and acts as the navigation hub of the app
It is mainly used to display a list of motorcycles, and also to save and delete its items.
* */

private val logger: BaseLogger = FactoryLogger.getLoggerCompose("MainApplicationScreen")

@Composable
fun MainApplicationScreen(
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: (event: MotorcycleListEvent) -> Unit
) {
    logger.debug("MainApplicationScreen()")
    val navController = rememberNavController()// navController used to control the navigation
    // Provide a place in the Compose hierarchy for self contained navigation to occur.
    NavHost(navController = navController, startDestination = Route.MotorcycleListScreen.name) {
        composable(route = Route.MotorcycleListScreen.name) {
            // Show list of motorcycles and an add button used to go to AddMotorcycleScreen()
            MotorcycleListScreen(
                navController = navController,
                motorcycleListState = motorcycleListState,
                motorcycleListEvent = motorcycleListEvent,
            )
        }
        composable(route = Route.AddMotorcycleScreen.name) {
            // Show a list of manufacturers, each containing different motorcycle models to choose
            // from and to display each of its attributes and to select them for saving into the
            // motorcycle list
            AddMotorcycleScreen(
                navController = navController,
                motorcycleListState = motorcycleListState,
                motorcycleListEvent = motorcycleListEvent
            )
        }
    }

}

