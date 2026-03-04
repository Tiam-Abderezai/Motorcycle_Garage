package com.example.motorcyclegarage.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.motorcyclegarage.ui.theme.MotorcycleGarageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MotorcycleGarageTheme {
                Surface() {
                    ApplicationScreen()
                }
            }
        }
    }
}
