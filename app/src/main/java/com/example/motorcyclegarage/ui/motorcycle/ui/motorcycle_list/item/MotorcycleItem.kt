package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.item


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
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState


/* MotorcycleItem() shows one individual item of a MotorCycle object
* inside the LazyColumn list of the MainScreen
*  */
@Composable
fun MotorcycleItem(
    motorcycle: Motorcycle,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavHostController,
    index: Int
) {
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
                painter = painterResource(id = motorcycle.logo),
                contentDescription = null
            )
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = motorcycle.image!!),
                contentDescription = null
            )

            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "        ${motorcycle.model?.name!!}")
        }
        if (expandedItemIndex) MotorcycleItemSection(
            motorcycle,
            motorcycleListState,
            motorcycleListEvent,
            navController,
            index
        )
    }
}