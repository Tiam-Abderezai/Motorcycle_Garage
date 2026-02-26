package com.example.motorcyclegarage.motorcycle.crud

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.motorcyclegarage.addButtonClicked
import com.example.motorcyclegarage.motorcycle.model.Manufacturer
import com.example.motorcyclegarage.motorcycle.model.manufacturerList

var isManufacturerClicked by mutableStateOf(false)
var isModelClicked by mutableStateOf(false)
var selectedModelIndex by mutableStateOf(0)

@Composable
fun AddMotorcycleScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Gray)
    ) {
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        SelectManufacturerMenu()

    }

    addButtonClicked = false
}


@Composable
private fun SelectManufacturerMenu() {
// Smoothly scroll 100px on first composition

    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(560.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(manufacturerList) { index, manu ->
            ManufacturerMenuItem(manu)
        }

    }
}

@Composable
fun ManufacturerMenuItem(manu: Manufacturer) {
    var expandedItemIndex by remember { mutableStateOf(false) }

    Image(
        modifier = Modifier
            .size(64.dp)
            .clickable {
                if (expandedItemIndex) {
                    expandedItemIndex = false
                } else expandedItemIndex = true
            },
        painter = painterResource(id = manu.logo),
        contentDescription = null,
    )
    Spacer(
        modifier = Modifier
            .background(Color.DarkGray)
            .width(128.dp)
            .height(2.dp)
    )
    if (expandedItemIndex) SelectModelMenu(manu)
}

@Composable
private fun SelectModelMenu(manufacturer: Manufacturer) {
// Smoothly scroll 100px on first composition

    LazyColumn(
        modifier = Modifier
            .background(Color.DarkGray)
            .size(255.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(manufacturer.models) { index, model ->
            Image(
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
                    .clickable {
                        isModelClicked = true
                        selectedModelIndex = index
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
        Text(text = "MANUFACTURER: ${manufacturer.name}")
        Text(text = "MODEL: ${manufacturer.models[selectedModelIndex].name}")
        Text(text = "YEAR: ${manufacturer.models[selectedModelIndex].year}")
        Text(text = "POWER: ${manufacturer.models[selectedModelIndex].power}")
        Text(text = "TYPE: ${manufacturer.models[selectedModelIndex].type}")
    }
}
