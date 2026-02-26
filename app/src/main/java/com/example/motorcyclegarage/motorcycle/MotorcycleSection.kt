package com.example.motorcyclegarage.motorcycle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.motorcyclegarage.motorcycle.model.Motorcycle

@Composable
fun MotorcycleSection(motor: Motorcycle) {
    Box() {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column() {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = motor.logo),
                    contentDescription = null
                )
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = motor.image),
                    contentDescription = null
                )
                Text(text = "MANUFACTURER: ${motor.manufacturer}")
                Text(text = "MODEL: ${motor.model.name}")
                Text(text = "YEAR: ${motor.model.name}")
                Text(text = "POWER: ${motor.model.power}")
                Text(text = "TYPE: ${motor.model.type}")
            }
        }
    }
}