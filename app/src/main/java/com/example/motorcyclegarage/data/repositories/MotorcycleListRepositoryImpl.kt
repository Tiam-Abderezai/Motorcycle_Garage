package com.example.motorcyclegarage.data.repositories

import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDAO
import com.example.motorcyclegarage.data.model.motorcycle.Motorcycle
import kotlinx.coroutines.flow.Flow
private val logger: BaseLogger = FactoryLogger.getLoggerKClass(MotorcycleListRepositoryImpl::class)

class MotorcycleListRepositoryImpl(
    private val motorcycleListDao: MotorcycleListDAO,
) : MotorcycleListRepository {


    override val allMotorcycles: Flow<List<Motorcycle>> =
        motorcycleListDao.getAllMotorcycles()


    override suspend fun insertMotorcycle(motorcycle: Motorcycle) {
        logger.debug("insertMotorcycle() - motorcycle: ${motorcycle}")
        motorcycleListDao.insertMotorcycle(motorcycle)
    }

    override suspend fun deleteMotorcycle(motorcycleId: Int) {
        logger.debug("deleteMotorcycle() - motorcycleId: ${motorcycleId}")
        motorcycleListDao.deleteMotorcycle(motorcycleId)
    }

}