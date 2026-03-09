package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.ui.components.EmptyMotorcycleList
import com.example.motorcyclegarage.ui.components.ErrorMotrcycleList
import com.example.motorcyclegarage.ui.components.LoadingScreen
import com.example.motorcyclegarage.ui.components.Route
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.item.MotorcycleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val logger: BaseLogger = FactoryLogger.getLoggerCompose("MotorcycleListScreen")

private var isAddClicked by mutableStateOf(false)

@Composable
fun MotorcycleListScreen(
    navController: NavHostController,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit
) {
    when (motorcycleListState) {
        is MotorcycleListState.Initial -> {
            LaunchedEffect(Unit) {
                withContext(Dispatchers.IO) {
                    motorcycleListEvent.invoke(
                        MotorcycleListEvent.GetMotorcycleList()
                    )
                    logger.debug("${MotorcycleListState.Initial}")
                }
            }
        }

        is MotorcycleListState.Loading -> {
            LoadingScreen()
            logger.debug("${MotorcycleListState.Loading}")
        }

        is MotorcycleListState.Empty -> {
            EmptyMotorcycleList()
            MotorcycleListSection(
                emptyList(),
                motorcycleListState,
                motorcycleListEvent,
                navController
            )
            logger.debug("${MotorcycleListState.Empty}")
        }

        is MotorcycleListState.Error -> {
            ErrorMotrcycleList()
            logger.debug("MotorcycleListState.Error")
        }

        is MotorcycleListState.Complete -> {
            MotorcycleListSection(
                motorcycleListState.motorcycleList,
                motorcycleListState,
                motorcycleListEvent,
                navController
            )
            logger.debug("MotorcycleListState.Complete")
        }
    }

}

@Composable
fun MotorcycleListSection(
    motorcycleList: List<Motorcycle>,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavHostController
) {
    // Fade in animation for the entire section
    val fadeInAnimation by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 500),
        label = "fadeInAnimation"
    )

    Box(
        modifier = Modifier
            .padding(vertical = 80.dp)
            .background(Color.Black)
            .graphicsLayer { alpha = fadeInAnimation }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 80.dp)
                .background(Color.Gray)
        ) {
            // Animated LazyColumn with staggered item appearance
            val listState = rememberLazyListState()
            val scope = rememberCoroutineScope()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                itemsIndexed(motorcycleList) { index, motor ->
                    // Staggered animation for each item
                    val delay = index * 100L
                    var isVisible by remember { mutableStateOf(false) }

                    LaunchedEffect(Unit) {
                        delay(delay)
                        isVisible = true
                    }

                    val alpha by animateFloatAsState(
                        targetValue = if (isVisible) 1f else 0f,
                        animationSpec = tween(durationMillis = 500)
                    )

                    val offsetY by animateDpAsState(
                        targetValue = if (isVisible) 0.dp else 20.dp,
                        animationSpec = tween(durationMillis = 500)
                    )

                    MotorcycleItem(
                        modifier = Modifier
                            .graphicsLayer { this.alpha = alpha }
                            .offset(y = offsetY),
                        motorcycle = motor,
                        motorcycleListState = motorcycleListState,
                        motorcycleListEvent = motorcycleListEvent,
                        navController = navController,
                        index = index
                    )
                }
            }

            // Auto-scroll to top when content changes
            LaunchedEffect(motorcycleList) {
                scope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        }

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
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .graphicsLayer {
                    scaleX = pulseScale
                    scaleY = pulseScale
                }
        ) {
            ButtonAddMotorcycle {
                isAddClicked = true
            }
        }
    }

    // Navigation with slide animation
    if (isAddClicked) {
        navController.navigate(Route.AddMotorcycleScreen.name) {
            anim {
                slideInHorizontally(initialOffsetX = { it })
                slideOutHorizontally(targetOffsetX = { -it })
            }
        }
        isAddClicked = false
    }
}


@ExperimentalMaterial3Api
@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                color = Color.White,
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { iconClickAction.invoke() }) {
                Icon(imageVector = imageVector, contentDescription = "")
            }
        }
    )
}

@Composable
fun ButtonAddMotorcycle(
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = Modifier
            .size(64.dp),
        onClick = onClick,
        containerColor = Color.LightGray,  // Changed background color to Gray
        contentColor = Color.DarkGray    // Ensures icon remains visible
    ) {
        Icon(
            painterResource(id = R.drawable.icon_add),
            contentDescription = "Add"
        )
    }
}