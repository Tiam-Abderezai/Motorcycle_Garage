package com.example.motorcyclegarage.ui.motorcycle.ui.add_motorcycle

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
import com.example.motorcyclegarage.ui.dialog.AlertDialog
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.Period


private var isManufacturerItemClicked by mutableStateOf(false)
private var isModelClicked by mutableStateOf(false)
private var showAreYouSureSaveItemDialog by mutableStateOf(false)
private var showItemMustBeSelectedToSaveDialog by mutableStateOf(false)
private var isSaveClicked by mutableStateOf(false)
private var selectedModelIndex by mutableStateOf(0)
private var selectedManufacturerIndex by mutableStateOf(0)
private var selectedMotorcycleItem by mutableStateOf(motorcycleDummy)
private val logger: BaseLogger = FactoryLogger.getLoggerCompose("AddMotorcycleScreen")

/*AddMotorcycleScreen() acts as state-hoisting hub for the screen, where different states
(Loading, Completed, Error, etc) and events (saveMotorcycleList) while each
state and event is handled accordingly one at a time. This reduces side-effects in Compose and
ensures UDF (Uni-Directional-Flow) principles, by simplifying the handling of different states
of the screen, collected from the MotorcycleListViewModel, and events handled by the MotorcycleScreen()
view.
*/
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
// Floating button with pulse animation
    val infiniteTransition = rememberInfiniteTransition()
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SelectManufacturerMenu()
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(alignment = End)
                .graphicsLayer {
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
        ) {
            ButtonSaveMotorcycle()
            {
                logger.debug("Save button clicked")
                if (isModelClicked) {
                    showAreYouSureSaveItemDialog = true
                }
                if (selectedMotorcycleItem.manufacturer.id != selectedManufacturerIndex) {
                    showItemMustBeSelectedToSaveDialog = true
                }
            }
        }
    }

    if (showItemMustBeSelectedToSaveDialog) {
        AlertDialog(
            onDismissRequest = {
                showItemMustBeSelectedToSaveDialog = false
            },  // Called when user clicks outside dialog
            onConfirmation = {
                isSaveClicked = false
                // Add your confirmation action here
                // For example: navController.navigate(SomeRoute)
            },
            alertDialogTitle = "",
            alertDialogDescription = "You must first select a manufacturer and a model.",
            isConfirmationYesOrNo = false,
            alertDialogIcon = R.drawable.icon_checked
        )
    }

    if (showAreYouSureSaveItemDialog) {
        AlertDialog(
            onDismissRequest = {
                showAreYouSureSaveItemDialog = false
            },  // Called when user clicks outside dialog
            onConfirmation = {
                isSaveClicked = true
                // Add your confirmation action here
                // For example: navController.navigate(SomeRoute)
            },
            alertDialogTitle = "",
            alertDialogDescription = "Are you sure you want to add a new motorcycle?",
            isConfirmationYesOrNo = true,
            alertDialogIcon = R.drawable.icon_new
        )
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
        showAreYouSureSaveItemDialog = false
        showItemMustBeSelectedToSaveDialog = false
        isModelClicked = false
    }
}

@Composable
private fun SelectManufacturerMenu() {
    logger.debug("SelectManufacturerMenu - Displaying manufacturers")
    LazyColumn(
        modifier = Modifier
            .background(Color.Gray)
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
            .size(128.dp)
            .clickable {
                logger.debug("Manufacturer clicked: ${manufacturer.name}")
                expandedItemIndex = !expandedItemIndex
                selectedManufacturerIndex = manufacturer.id
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

    if (selectedManufacturerIndex == manufacturer.id && expandedItemIndex) { // Todo improve this line
        logger.debug("Showing models for: ${manufacturer.name}")
        SelectModelMenu(manufacturer)
    }
}

@Composable
private fun SelectModelMenu(manufacturer: Manufacturer) {
    logger.debug("SelectModelMenu - Manufacturer: ${manufacturer.name}")

    LazyRow(// Show the list of motorcycles horizontally.
        modifier = Modifier
            .background(Color.Blue)
            .width(480.dp)
            .height(320.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(createModelListTestData(manufacturer.id)) { index, model ->
            logger.debug("Displaying model: ${model.name}")

            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(64.dp)
            )
            Image(
                modifier = Modifier
                    .width(328.dp)
                    .height(255.dp)
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
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_right),
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(64.dp)
            )
        }
    }

    if (isModelClicked) {
        logger.debug("Displaying selected model details")
        val entranceDelay = 100L

        // Text with staggered appearance
        @Composable
        fun AnimatedText(
            label: String,
            value: String,
            delay: Long,
            style: TextStyle
        ) {
            var textVisible by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(entranceDelay + delay)
                textVisible = true
            }

            val textAlpha by animateFloatAsState(
                targetValue = if (textVisible) 1f else 0f,
                animationSpec = tween(durationMillis = 300),
                label = "${label}Text"
            )

            Column(
                modifier = Modifier
                    .background(Color.DarkGray)
                    .width(162.dp)
            ) {
                Text(
                    color = Color.Blue,
                    text = label,
                    style = style.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.alpha(textAlpha)
                )
                Text(
                    color = Color.Yellow,
                    text = "   $value",
                    style = style.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.alpha(textAlpha)
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxSize()
                    .height(1.dp)
            )
        }

        AnimatedText(
            label = "Manufacturer:",
            value = manufacturer.name,
            delay = 100L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )

        AnimatedText(
            label = "Model:",
            value = manufacturer.models!![selectedModelIndex].name,
            delay = 200L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )
        AnimatedText(
            label = "Created:",
            // Calculate the age from model dateCreated against current date
            value = manufacturer.models[selectedModelIndex].dateCreated.toString(),
            delay = 300L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )
        AnimatedText(
            label = "Age:",
            // Calculate the age from model dateCreated against current date
            value = calculateAge(manufacturer.models[selectedModelIndex].dateCreated).toString(),
            delay = 300L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )

        AnimatedText(
            label = "Power:",
            value = manufacturer.models[selectedModelIndex].power,
            delay = 400L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )

        AnimatedText(
            label = "Type:",
            value = manufacturer.models[selectedModelIndex].type,
            delay = 500L,
            style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
        )
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
        onClick = onClick,
        containerColor = Color.LightGray,  // Changed background color to Gray
        contentColor = Color.DarkGray    // Ensures icon remains visible
    ) {
        Icon(
            painterResource(id = R.drawable.icon_save),
            contentDescription = "Save"
        )
    }
}

private fun calculateAge(date: LocalDate): Int {
    val birthDate = LocalDate.of(date.year, date.month, date.dayOfMonth)
    val currentDate = LocalDate.now()
    return Period.between(birthDate, currentDate).years
}