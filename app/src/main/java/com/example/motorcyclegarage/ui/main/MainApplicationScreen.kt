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

/* The Main Screen is used to show all motorcycles the user has created.
* It also is used to go to other screens like MotorcycleScreen, and to
* add, delete, or edit motorcycle list items.
* */

private val logger: BaseLogger = FactoryLogger.getLoggerCompose("MainApplicationScreen")

@Composable
fun MainApplicationScreen(
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: (event: MotorcycleListEvent) -> Unit
) {
// Show the list of Motorcycles
    logger.debug("MainApplicationScreen()")
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.MotorcycleListScreen.name) {
        composable(route = Route.MotorcycleListScreen.name) {
            MotorcycleListScreen(
                navController = navController,
                motorcycleListState = motorcycleListState,
                motorcycleListEvent = motorcycleListEvent,
            )
        }
        composable(route = Route.AddMotorcycleScreen.name) {
            AddMotorcycleScreen(
                navController = navController,
                motorcycleListState = motorcycleListState,
                motorcycleListEvent = motorcycleListEvent
            )
        }
    }

}

