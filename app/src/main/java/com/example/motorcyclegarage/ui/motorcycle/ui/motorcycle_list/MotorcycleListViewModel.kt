package com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import com.example.motorcyclegarage.data.repositories.MotorcycleListRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private val logger: BaseLogger = FactoryLogger.getLoggerKClass(MotorcycleListViewModel::class)

class MotorcycleListViewModel(
    private val motorcycleListRepositoryImpl: MotorcycleListRepositoryImpl
) : ViewModel() {

    private val isError: MutableState<Boolean> = mutableStateOf(false)
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
    private val _state =
        MutableStateFlow<MotorcycleListState>(MotorcycleListState.Initial)
    val state: StateFlow<MotorcycleListState> = _state.asStateFlow()

    fun handleMotorcycleListEvent(event: MotorcycleListEvent) {
        when (event) {
            is MotorcycleListEvent.GetMotorcycleList -> getAllMotorcycles()
            is MotorcycleListEvent.SaveMotorcycleItem -> saveMotorcycle(event.motorcycle)
            is MotorcycleListEvent.DeleteMotorcycleItem -> deleteMotorcycle(event.motorcycle.id)
        }
    }

    @VisibleForTesting
    fun getAllMotorcycles() {
        viewModelScope.launch {
            try {
                motorcycleListRepositoryImpl.allMotorcycles
                    // Collect from allMotorcycles repository-impl
                    .collect { motorList ->
                        // Sort motorcycle list by manufacturer id
                        val sortedMotorcycleList = motorList.sortedBy { it.manufacturer.id }
                        // Pass collected motorList to Complete UI state and post it to mutable state flow
                        logger.debug("getAllMotorcycles() - motorList.size: ${motorList.size}")
                        _state.value = MotorcycleListState.Complete(motorcycleList = sortedMotorcycleList)
                    }
            } catch (e: Exception) {
                logger.debug("getAllMotorcycles() - Exception: ${e.message}")
                _state.value = MotorcycleListState.Error(message = e.message ?: "Unknown Error")
                _state.value = MotorcycleListState.Empty
            }
        }

    }

    @VisibleForTesting
    fun saveMotorcycle(motorcycle: Motorcycle) {
        viewModelScope.launch {
            try {
                // First load Loading UI state
                _state.value = MotorcycleListState.Loading
                // Then pass motorcycle to insertMotorcycle repository-impl
                logger.debug("saveMotorcycle() - motorcycle: ${motorcycle}")

                motorcycleListRepositoryImpl.insertMotorcycle(motorcycle)
            } catch (e: Exception) {
                _state.value = MotorcycleListState.Error(message = e.message ?: "Unknown Error")
                _state.value = MotorcycleListState.Empty
            }
        }
    }

    @VisibleForTesting
    fun deleteMotorcycle(motorcycleId: Int) {
        viewModelScope.launch {
            try {
                // First load Loading UI state
                _state.value = MotorcycleListState.Loading
                // Then pass motorcycle to insertMotorcycle repository-impl
                logger.debug("deleteMotorcycle() - motorcycleId = ${motorcycleId}")
                motorcycleListRepositoryImpl.deleteMotorcycle(motorcycleId)
            } catch (e: Exception) {
                _state.value = MotorcycleListState.Error(message = e.message ?: "Unknown Error")
                _state.value = MotorcycleListState.Empty
            }
        }
    }
}