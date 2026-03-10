package com.example.motorcyclegarage.di

import androidx.room.Room
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleListDatabase
import com.example.motorcyclegarage.data.repositories.MotorcycleListRepositoryImpl
import com.example.motorcyclegarage.ui.motorcycle.ui.motorcycle_list.MotorcycleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/*
Modules class uses Koin dependency injection to instantiate the different services like viewmodel,
repository, and database and inject them from the application class to the MainActivity where
the viewmodel is instantiated and converted into states and events to be passed for observation
and reaction for changes.
 */
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