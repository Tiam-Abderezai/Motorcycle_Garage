package com.example.motorcyclegarage.di

import androidx.room.Room
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDAO
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDatabase
import com.example.motorcyclegarage.data.repositories.MotorcycleListRepository
import com.example.motorcyclegarage.data.repositories.MotorcycleListRepositoryImpl
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MotorcycleListViewModel(motorcycleListRepositoryImpl = get())
    }
}

val repositoryModule = module {
    single {
        MotorcycleListRepositoryImpl(get())
    }
}
val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(get(), MotorcycleListDatabase::class.java, "MotorcycleDB").build()
    }
    single { get<MotorcycleListDatabase>().motorcycleListDAO() }
}