package com.example.motorcyclegarage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.motorcyclegarage.R
import com.example.motorcyclegarage.main.addButtonClicked
import com.example.motorcyclegarage.motorcycle.MotorcycleItem
import com.example.motorcyclegarage.motorcycle.Screen
import com.example.motorcyclegarage.motorcycle.model.Motorcycle


@Composable
fun MainScreen(motorList: ArrayList<Motorcycle>, navController: NavHostController) {

    Box(
        modifier = Modifier
            .padding(vertical = 80.dp)
            .background(Color.Cyan)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 80.dp)
                .background(Color.Blue)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed(motorList) { index, motor ->
                    MotorcycleItem(motor)
                }
            }

        }
        Box(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomEnd)
        ) {
            ButtonAddMotorcycle({
                addButtonClicked = true
            })
        }
    }
    if (addButtonClicked) navController.navigate(Screen.AddMotorcycleScreen.route)
}

@ExperimentalMaterial3Api
@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White
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
        onClick = onClick
    ) {
        Icon(
            painterResource(id = R.drawable.icon_add),
            contentDescription = "Add"
        )
    }
}