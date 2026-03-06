package com.example.motorcyclegarage.data.database.motorcycle

import android.content.Context
//import com.example.motorcyclegarage.motorcycle.motorcycleList

interface MotorcycleRepository {

    suspend fun addMotorcycle()
}

class MotorcycleRepositoryImpl(
    private val motorcycleDAO: MotorcycleDAO,
    private val context: Context
) : MotorcycleRepository {
    companion object {
        private val TAG = MotorcycleRepositoryImpl::class.java.simpleName
    }


    override suspend fun addMotorcycle() {
        // Check if app is online on any network
        println("TIAMMM MotorcycleRepositoryImpl")
//        runBlocking {
//        motorcycleList.forEach { motorcycleDAO.insertMotorcycle(it) }
//        }
    }
//        else {
//            // if connection is lost, try launching app, it should show db data
//            val teamInDB = teamDao.queryNBATeams()
//            return if (teamInDB.isNotEmpty()) {
//                Log.d(com.shreyasmp.nbateams.repository.NBATeamsRepositoryImpl.Companion.TAG, "From Database: ")
//                ResultWrapper.SUCCESS(teamInDB)
//            } else {
//                ResultWrapper.FAILURE("407")
//            }
//        }
}