package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.item


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListEvent
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListState


/* MotorcycleItem() shows one individual item of a MotorCycle object
* inside the LazyColumn list of the MainScreen
*  */
@Composable
fun MotorcycleItem(
    modifier: Modifier,
    motorcycle: Motorcycle,
    motorcycleListState: MotorcycleListState,
    motorcycleListEvent: suspend (MotorcycleListEvent) -> Unit,
    navController: NavHostController,
    index: Int
) {
    // Staggered entrance animation delay based on item index
    val entranceDelay = index * 100L

    // Animation state for item appearance
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(entranceDelay)
        isVisible = true
    }

    // Fade and slide animation values
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "itemFade"
    )

    val offsetY by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 20.dp,
        animationSpec = tween(durationMillis = 500),
        label = "itemSlide"
    )

    // Expansion animation state
    var expandedItemIndex by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expandedItemIndex) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "expandRotation"
    )

    // Content scale animation for expanded state
    val contentScale by animateFloatAsState(
        targetValue = if (expandedItemIndex) 1f else 0.95f,
        animationSpec = tween(durationMillis = 300),
        label = "contentScale"
    )
    Box(
        modifier = modifier
            .background(Color.DarkGray)
            .graphicsLayer {
                this.scaleX = contentScale
                this.scaleY = contentScale
            }
    ) {
        Row(
            modifier = Modifier
                .clickable {
                    expandedItemIndex = !expandedItemIndex
                }
                .fillMaxWidth()
                .graphicsLayer {
                    this.alpha = alpha
                    translationY = offsetY.toPx()
                }
        ) {
            // Logo image with rotation animation
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .graphicsLayer {
                        rotationZ = rotationAngle
                    },
                painter = painterResource(id = motorcycle.logo),
                contentDescription = null
            )

            // Motorcycle image with fade animation
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .padding(start = 8.dp)
                    .clip(RoundedCornerShape(16.dp)) // Adds rounded corners (16.dp radius)
                    .alpha(alpha),
                painter = painterResource(id = motorcycle.image!!),
                contentDescription = null
            )

            // Text with typewriter effect simulation
            var textProgress by remember { mutableStateOf(0f) }
            LaunchedEffect(expandedItemIndex) {
                if (expandedItemIndex) {
                    textProgress = 0f
                    // No actual typewriter animation to keep it simple
                }
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .alpha(alpha),
                text = "        ${motorcycle.model?.name!!}",
                color = Color.Black
            )
        }

        // Expanded content with slide down animation
        if (expandedItemIndex) {
            val expandAlpha by animateFloatAsState(
                targetValue = if (expandedItemIndex) 1f else 0f,
                animationSpec = tween(durationMillis = 300),
                label = "expandFade"
            )

            val expandOffset by animateDpAsState(
                targetValue = if (expandedItemIndex) 0.dp else (-20).dp,
                animationSpec = tween(durationMillis = 300),
                label = "expandSlide"
            )

            MotorcycleItemSection(
                modifier = Modifier
                    .graphicsLayer {
                        this.alpha = expandAlpha
                        translationY = expandOffset.toPx()
                    },
                motorcycle = motorcycle,
                motorcycleListState = motorcycleListState,
                motorcycleListEvent = motorcycleListEvent,
                navController = navController,
                index = index
            )
        }
    }
    Spacer(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .height(2.dp)
    )
}