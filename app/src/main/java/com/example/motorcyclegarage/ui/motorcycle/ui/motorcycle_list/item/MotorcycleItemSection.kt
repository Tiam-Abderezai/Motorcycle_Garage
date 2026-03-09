package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.common.motorcycleDummy
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.ui.components.DeleteMessage
import com.example.motorcyclegarage.ui.components.Route
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private var isDeleteClicked by mutableStateOf(false)
private var selectedMotorcycleItem by mutableStateOf(motorcycleDummy)

private val logger: BaseLogger = FactoryLogger.getLoggerCompose("MotorcycleItemSection")

@Composable
fun MotorcycleItemSection(
    motorcycle: Motorcycle,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavHostController,
    index: Int
) {
    logger.debug("MotorcycleItemSection - Displaying motorcycle: ${motorcycle.manufacturer.name} ${motorcycle.model?.name}")

    Box() {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column() {
                logger.debug("Displaying manufacturer logo: ${motorcycle.manufacturer.name}")
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = motorcycle.logo),
                    contentDescription = null
                )

                logger.debug("Displaying motorcycle image: ${motorcycle.model?.name}")
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = motorcycle.image!!),
                    contentDescription = null
                )

                logger.debug("Rendering delete button for motorcycle ID: ${motorcycle.id}")
                ButtonDeleteMotorcycle(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        logger.debug("Delete button clicked for motorcycle ID: ${motorcycle.id}")
                        isDeleteClicked = true
                        selectedMotorcycleItem = motorcycle
                    }
                )

                logger.debug("Displaying motorcycle details")
                Text(
                    text = "Manufacturer:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                Text(
                    text = "                          ${motorcycle.manufacturer.name}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Text(
                    text = "MODEL:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                Text(
                    text = "                          ${motorcycle.model?.name}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Text(
                    text = "YEAR:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                Text(
                    text = "                          ${motorcycle.model?.year}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Text(
                    text = "POWER:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                Text(
                    text = "                          ${motorcycle.model?.power}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
                Text(
                    text = "TYPE:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize
                    )
                )
                Text(
                    text = "                          ${motorcycle.model?.type}",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                )
            }

            if (isDeleteClicked && motorcycle.id == selectedMotorcycleItem.id) {
                logger.debug("Deleting motorcycle with ID: ${motorcycle.id}")
                val motorcycleToDelete = selectedMotorcycleItem
                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        motorcycleListEvent.invoke(
                            MotorcycleListEvent.DeleteMotorcycleItem(motorcycleToDelete)
                        )
                        logger.debug("Motorcycle deleted successfully: ${motorcycleToDelete.id}")
                    }
                }
                DeleteMessage()
                isDeleteClicked = false
            }
        }
    }
}

@Composable
fun ButtonDeleteMotorcycle(
    onClick: () -> Unit,
    modifier: Modifier
) {
    logger.debug("ButtonDeleteMotorcycle - Rendered")
    FloatingActionButton(
        modifier = modifier
            .background(Color.DarkGray)
            .size(64.dp),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = R.drawable.icon_delete),
            contentDescription = "Delete"
        )
    }
}
