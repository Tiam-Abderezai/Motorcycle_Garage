package com.example.motorcyclegarage.ui.motorcycle.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle

/* MotorcycleItem() shows one individual item of a MotorCycle object
* inside the LazyColumn list of the MainScreen
*  */
@Composable
fun MotorcycleItem(motor: Motorcycle) {
    Box() {
        var expandedItemIndex by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .clickable {
                    if (expandedItemIndex) {
                        expandedItemIndex = false
                    } else expandedItemIndex = true
                }
                .fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = motor.logo),
                contentDescription = null
            )
//            Text(text = motor.model)
        }
        if (expandedItemIndex) MotorcycleSection(motor)
    }
}