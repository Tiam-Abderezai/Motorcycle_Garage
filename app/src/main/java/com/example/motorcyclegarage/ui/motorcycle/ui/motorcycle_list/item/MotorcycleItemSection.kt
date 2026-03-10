package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.item

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.motorcyclegarage.ui.dialog.AlertDialog
import com.example.motorcyclegarage.ui.motorcycle.ui.add_motorcycle.calculateAge
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate
import org.joda.time.Years
import java.util.Date

private var isDeleteClicked by mutableStateOf(false)
private var selectedMotorcycleItem by mutableStateOf(motorcycleDummy)
private var selectedModelIndex by mutableStateOf(0)

private val logger: BaseLogger = FactoryLogger.getLoggerCompose("MotorcycleItemSection")

// Hosted by MotorcycleListScreen(), MotorcycleItemSection() displays individual attributes of
// MotorcycleItem() as well as handling delete button and alert dialog
@Composable
fun MotorcycleItemSection(
    modifier: Modifier,
    motorcycle: Motorcycle,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavHostController,
    index: Int
) {
    logger.debug("MotorcycleItemSection - Displaying motorcycle: ${motorcycle.manufacturer.name} ${motorcycle.model?.name}")
    var showDialog by remember { mutableStateOf(false) }
    // Staggered entrance animation delay based on index
    val entranceDelay = 100L
    selectedModelIndex = index
    // Animation state for initial appearance
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(entranceDelay)
        isVisible = true
    }

    // Fade animation
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "fadeAnimation"
    )

    // Slide up animation
    val offsetY by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 50.dp,
        animationSpec = tween(durationMillis = 500),
        label = "slideAnimation"
    )

    // Scale animation for delete button
    var isDeleteHovered by remember { mutableStateOf(false) }
    val deleteButtonScale by animateFloatAsState(
        targetValue = if (isDeleteHovered) 1.1f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = "deleteButtonScale"
    )

    // Content scale animation when deleting
    val contentScale by animateFloatAsState(
        targetValue = if (isDeleteClicked && motorcycle.id == selectedMotorcycleItem.id) 0.9f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "contentScale"
    )
    Box(
        modifier = modifier
            .alpha(alpha)
            .offset(y = offsetY)
            .graphicsLayer {
                scaleX = contentScale
                scaleY = contentScale
            }
    ) {
        Row(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .alpha(alpha)
            ) {
                // Logo with subtle pulse animation
                val logoPulse by animateFloatAsState(
                    targetValue = if (isVisible) 1.05f else 1f,
                    animationSpec = tween(durationMillis = 1000),
                    label = "logoPulse"
                )

                Image(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp)) // Adds rounded corners (16.dp radius)
                        .graphicsLayer {
                            scaleX = logoPulse
                            scaleY = logoPulse
                        },
                    painter = painterResource(id = motorcycle.logo),
                    contentDescription = null
                )

                // Main image with fade in
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(alpha),
                    painter = painterResource(id = motorcycle.image!!),
                    contentDescription = null
                )

                // Delete button with hover animation
                ButtonDeleteMotorcycle(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                        .graphicsLayer {
                            scaleX = deleteButtonScale
                            scaleY = deleteButtonScale
                        },
                    onClick = {
                        logger.debug("Delete button clicked for motorcycle ID: ${motorcycle.id}")
                        showDialog = true
                        selectedMotorcycleItem = motorcycle
                    }
                )
                MotorcycleItemSectionMenu(motorcycle)
            }

            if (showDialog && motorcycle.id == selectedMotorcycleItem.id) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },  // Called when user clicks outside dialog
                    onConfirmation = {
                        isDeleteClicked = true
                        // Add your confirmation action here
                        // For example: navController.navigate(SomeRoute)
                    },
                    alertDialogTitle = "",
                    alertDialogDescription = "Are you sure you want to delete a new motorcycle?",
                    isConfirmationYesOrNo = true,
                    alertDialogIcon = R.drawable.icon_hand_stop
                )
            }

            // Delete animation
            if (isDeleteClicked) {
                val motorcycleToDelete = selectedMotorcycleItem

                // Fade out and shrink animation
                val deleteAlpha by animateFloatAsState(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 300),
                    label = "deleteFade"
                )

                val deleteScale by animateFloatAsState(
                    targetValue = 0.8f,
                    animationSpec = tween(durationMillis = 300),
                    label = "deleteScale"
                )

                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        motorcycleListEvent.invoke(
                            MotorcycleListEvent.DeleteMotorcycleItem(motorcycleToDelete)
                        )
                        logger.debug("Motorcycle deleted successfully: ${motorcycleToDelete.id}")
                    }
                }

                Box(
                    modifier = Modifier
                        .graphicsLayer {
                            this.alpha = deleteAlpha
                            scaleX = deleteScale
                            scaleY = deleteScale
                        }
                ) {
                    DeleteMessage()
                }

                // Reset after animation completes
                LaunchedEffect(deleteAlpha) {
                    if (deleteAlpha == 0f) {
                        isDeleteClicked = false
                    }
                }
            }
        }
    }
}

@Composable
fun MotorcycleItemSectionMenu(motorcycle: Motorcycle) {
    // Staggered entrance animation delay based on index
    val entranceDelay = 100L
    val yearLong = motorcycle.model?.dateCreated
    val yearCreatedDate = yearLong?.let { LocalDate.fromDateFields(Date(it)) }
    val yearAgeDate = yearCreatedDate?.let { calculateAge(it) }.toString()

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
            delay(entranceDelay + delay)
            textVisible = true
        }

        val textAlpha by animateFloatAsState(
            targetValue = if (textVisible) 1f else 0f,
            animationSpec = tween(durationMillis = 300),
            label = "${label}Text"
        )

        Column(
            modifier = Modifier
                .background(Color(0xFF535353)) // Todo Declare this into a variable
                .fillMaxWidth()

        ) {
            Text(
                color = Color.Cyan,
                text = label,
                style = style.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.alpha(textAlpha)
            )
            Text(
                color = Color.Yellow,
                text = "                          $value",
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
        value = motorcycle.manufacturer.name,
        delay = 100L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )

    AnimatedText(
        label = "Model:",
        value = motorcycle.model?.name ?: "",
        delay = 200L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )

    AnimatedText(
        label = "Created:",
        value = yearCreatedDate?.year.toString(),
        delay = 300L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )

    AnimatedText(
        label = "Age:",
        value = yearAgeDate,
        delay = 400L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )

    AnimatedText(
        label = "Power:",
        value = motorcycle.model?.power ?: "",
        delay = 500L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )

    AnimatedText(
        label = "Type:",
        value = motorcycle.model?.type ?: "",
        delay = 600L,
        style = TextStyle(fontSize = MaterialTheme.typography.bodyLarge.fontSize)
    )
}
@Composable
fun ButtonDeleteMotorcycle(
    onClick: () -> Unit,
    modifier: Modifier
) {
    logger.debug("ButtonDeleteMotorcycle - Rendered")

    // Button press animation
    var isPressed by remember { mutableStateOf(false) }
    val buttonScale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "buttonPress"
    )

    FloatingActionButton(
        modifier = modifier
            .background(Color.DarkGray)
            .size(64.dp)
            .graphicsLayer {
                scaleX = buttonScale
                scaleY = buttonScale
            },
        onClick = onClick,
        containerColor = Color.LightGray,  // Changed background color to Gray
        contentColor = Color.DarkGray    // Ensures icon remains visible
    ) {
        Icon(
            painterResource(id = R.drawable.icon_delete),
            contentDescription = "Delete"
        )
    }
}

fun calculateAge(date: LocalDate): Int {
    val currentDate = LocalDate.now()
    return Years.yearsBetween(date, currentDate).years
}