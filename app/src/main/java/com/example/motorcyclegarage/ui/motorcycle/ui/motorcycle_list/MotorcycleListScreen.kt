package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                itemsIndexed(motorcycleList) { index, motor ->
                    MotorcycleItem(
                        motor,
                        motorcycleListState,
                        motorcycleListEvent,
                        navController,
                        index
                    )
                }
            }

        }
        Box(
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.BottomEnd)
        ) {
            ButtonAddMotorcycle({
                isAddClicked = true
            })
        }
    }
    if (isAddClicked) navController.navigate(Route.AddMotorcycleScreen.name)
    isAddClicked = false
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