package com.example.motorcyclegarage.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.motorcycleList
//import com.example.motorcyclegarage.motorcycle.motorcycleList
import com.example.motorcyclegarage.ui.motorcycle.ui.MotorcycleViewModel
import com.example.motorcyclegarage.ui.theme.MotorcycleGarageTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val motorcycleViewModel by viewModel<MotorcycleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MotorcycleGarageTheme {
                Surface() {
                    println("TIAMM MainActivity")
                    MainApplicationScreen()
                }
            }
        }
    }
}
