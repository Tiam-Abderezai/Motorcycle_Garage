package com.example.motorcyclegarage.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun DeleteMessage(
    modifier: Modifier = Modifier,
    visible: Boolean = false,
    durationMillis: Int = 2000,
    onDismiss: () -> Unit = {}
) {
    var showMessage by remember { mutableStateOf(visible) }

    LaunchedEffect(visible) {
        if (visible) {
            showMessage = true
            delay(durationMillis.toLong())
            showMessage = false
            onDismiss()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = showMessage,
            enter = slideInVertically(initialOffsetY = { -40 }) + expandVertically() + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -40 }) + shrinkVertically() + fadeOut(),
            modifier = Modifier
                .shadow(4.dp, shape = RoundedCornerShape(8.dp))
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF4CAF50), RoundedCornerShape(8.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Deleted!",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}