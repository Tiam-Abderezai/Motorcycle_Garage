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
import com.example.motorcyclegarage.data.repositories.MotorcycleListRepositoryImpl
//import com.example.motorcyclegarage.motorcycle.motorcycleList
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListViewModel
import com.example.motorcyclegarage.ui.theme.MotorcycleGarageTheme
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val logger: BaseLogger = FactoryLogger.getLoggerKClass(MainActivity::class)

    private val motorcycleListViewModel by inject<MotorcycleListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showSplash by remember { mutableStateOf(true) }

            val motorcycleListState = motorcycleListViewModel.state.collectAsState().value
            val motorcycleListEvent = motorcycleListViewModel::handleMotorcycleListEvent
            MotorcycleGarageTheme {
                Surface() {
                    if (showSplash) {
                        MainSplashScreen(
                            onTimeout = { showSplash = false }
                        )
                    } else {
                        logger.debug("MotorcycleGarageTheme Surface()")
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


