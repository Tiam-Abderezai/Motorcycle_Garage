package com.example.motorcyclegarage.ui.motorcycle.ui.add_motorcycle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.common.motorcycleDummy
import com.example.motorcyclegarage.data.model.motorcycle.Manufacturer
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.createManufacturerListTestData
import com.example.motorcyclegarage.motorcycle.MotorcycleProvider.createModelListTestData
import com.example.motorcyclegarage.ui.components.EmptyMotorcycleList
import com.example.motorcyclegarage.ui.components.ErrorMotrcycleList
import com.example.motorcyclegarage.ui.components.LoadingScreen
import com.example.motorcyclegarage.ui.components.SavedMessage
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID


private var isManufacturerClicked by mutableStateOf(false)
private var isModelClicked by mutableStateOf(false)
private var isSaveClicked by mutableStateOf(false)
var selectedModelIndex by mutableStateOf(0)
private var selectedMotorcycleItem by mutableStateOf(motorcycleDummy)
private val logger: BaseLogger = FactoryLogger.getLoggerCompose("AddMotorcycleScreen")

@Composable
fun AddMotorcycleScreen(
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: (MotorcycleListEvent) -> Unit,
    navController: NavController
) {
    logger.debug("AddMotorcycleScreen - State: $motorcycleListState")

    when (motorcycleListState) {
        is MotorcycleListState.Initial -> {
            logger.debug("MotorcycleListState.Initial")
        }

        is MotorcycleListState.Loading -> {
            logger.debug("MotorcycleListState.Loading")
            LoadingScreen()
        }

        is MotorcycleListState.Empty -> {
            logger.debug("MotorcycleListState.Empty")
            EmptyMotorcycleList()
            AddMotorcycleSection(
                emptyList(),
                motorcycleListEvent,
                navController
            )
        }

        is MotorcycleListState.Error -> {
            logger.debug("MotorcycleListState.Error")
            ErrorMotrcycleList()
        }

        is MotorcycleListState.Complete -> {
            logger.debug("MotorcycleListState.Complete - Items: ${motorcycleListState.motorcycleList.size}")
            AddMotorcycleSection(
                motorcycleListState.motorcycleList,
                motorcycleListEvent,
                navController
            )
        }
    }
}

@Composable
fun AddMotorcycleSection(
    motorcycleList: List<Motorcycle>,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavController
) {
    logger.debug("AddMotorcycleSection - Items: ${motorcycleList.size}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {}

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SelectManufacturerMenu()
        ButtonSaveMotorcycle {
            logger.debug("Save button clicked")
            isSaveClicked = true
        }
    }

    if (isSaveClicked) {
        logger.debug("Saving motorcycle: $selectedMotorcycleItem")
        val motorcycle = selectedMotorcycleItem
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                motorcycleListEvent.invoke(
                    MotorcycleListEvent.SaveMotorcycleItem(motorcycle)
                )
                logger.debug("Motorcycle saved successfully")
            }
        }
        SavedMessage()
        navController.popBackStack()
        isSaveClicked = false
    }
}

@Composable
private fun SelectManufacturerMenu() {
    logger.debug("SelectManufacturerMenu - Displaying manufacturers")
    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(560.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(createManufacturerListTestData()) { index, manufacturer ->
            logger.debug("Displaying manufacturer: ${manufacturer.name}")
            ManufacturerMenuItem(manufacturer)
        }
    }
}

@Composable
fun ManufacturerMenuItem(manufacturer: Manufacturer) {
    var expandedItemIndex by remember { mutableStateOf(false) }
    logger.debug("ManufacturerMenuItem - ${manufacturer.name} - Expanded: $expandedItemIndex")

    Image(
        modifier = Modifier
            .size(64.dp)
            .clickable {
                logger.debug("Manufacturer clicked: ${manufacturer.name}")
                expandedItemIndex = !expandedItemIndex
            },
        painter = painterResource(id = manufacturer.logo),
        contentDescription = null,
    )

    Spacer(
        modifier = Modifier
            .background(Color.DarkGray)
            .width(128.dp)
            .height(2.dp)
    )

    if (expandedItemIndex) {
        logger.debug("Showing models for: ${manufacturer.name}")
        SelectModelMenu(manufacturer)
    }
}

@Composable
private fun SelectModelMenu(manufacturer: Manufacturer) {
    logger.debug("SelectModelMenu - Manufacturer: ${manufacturer.name}")

    LazyColumn(
        modifier = Modifier
            .background(Color.DarkGray)
            .size(255.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(createModelListTestData(manufacturer.id)) { index, model ->
            logger.debug("Displaying model: ${model.name}")
            Image(
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
                    .background(if (isModelClicked && selectedModelIndex == index) Color.Yellow else Color.DarkGray)
                    .clickable {
                        logger.debug("Model selected: ${model.name}")
                        isModelClicked = true
                        selectedModelIndex = index
                        selectedMotorcycleItem = Motorcycle(
                            id = (System.currentTimeMillis() % 1000000).toInt(),  // Generates 6-digit integer ID
                            manufacturer = manufacturer,
                            logo = manufacturer.logo,
                            image = model.image,
                            model = model,
                        )
                    },
                painter = painterResource(id = model.image),
                contentDescription = null,
            )
            Spacer(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .width(128.dp)
                    .height(2.dp)
            )
        }
    }

    if (isModelClicked) {
        logger.debug("Displaying selected model details")
        Text(text = manufacturer.name)
        Text(text = manufacturer.models!![selectedModelIndex].name)
        Text(text = manufacturer.models[selectedModelIndex].year)
        Text(text = manufacturer.models[selectedModelIndex].power)
        Text(text = manufacturer.models[selectedModelIndex].type)
    }
}

@Composable
fun ButtonSaveMotorcycle(
    onClick: () -> Unit
) {
    logger.debug("ButtonSaveMotorcycle - Rendered")
    FloatingActionButton(
        modifier = Modifier
            .size(64.dp),
        onClick = onClick
    ) {
        Icon(
            painterResource(id = R.drawable.icon_save),
            contentDescription = "Save"
        )
    }
}
