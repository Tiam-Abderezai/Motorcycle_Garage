package com.example.motorcyclegarage.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListViewModel
import com.example.motorcyclegarage.ui.theme.MotorcycleGarageTheme
import org.koin.android.ext.android.inject

/* MainActivity() is the entry Activity called by MainActivity that starts with the SplashScreen()
followed by the MainApplicationScreen() that is the entry Composable and where mainly navigation occurs
*/
class MainActivity : ComponentActivity() {
    private val logger: BaseLogger = FactoryLogger.getLoggerKClass(MainActivity::class)

    private val motorcycleListViewModel by inject<MotorcycleListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showSplash by remember { mutableStateOf(true) }

            /* Instantiate the MotorcycleListViewModel instance of a state flow value of
            *  the type MotorcycleListState that is emitted from the ViewModel to the View
            *  for reading data from the business logic and data to the user interface or View
            * */
            val motorcycleListState = motorcycleListViewModel.state.collectAsState().value
            /* Instantiate the MotorcycleListViewModel instance of an event lambda value of
            *  the type MotorcycleListEvent that is triggered from the View to the ViewModel
            *  for reacting to changes done in the UI and sent to the business logic or database
            * */
            val motorcycleListEvent = motorcycleListViewModel::handleMotorcycleListEvent
            MotorcycleGarageTheme {
                Surface() {
                    if (showSplash) {
                        MainSplashScreen(
                            onTimeout = { showSplash = false } // Todo improve onTimeout mechanism
                        )
                    } else {
                        logger.debug("MotorcycleGarageTheme Surface()")
                        // MainApplicationScreen() Composable is the main navigation hub of the Application
                        MainApplicationScreen(
                            motorcycleListState = motorcycleListState,
                            motorcycleListEvent = motorcycleListEvent
                        )
                    }
                }
            }
        }
    }
}


