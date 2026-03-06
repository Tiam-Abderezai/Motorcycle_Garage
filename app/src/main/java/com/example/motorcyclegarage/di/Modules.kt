package com.example.motorcyclegarage.di

import android.content.Context
import androidx.room.Room
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleDAO
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleDatabase
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleRepository
import com.example.motorcyclegarage.data.database.motorcycle.MotorcycleRepositoryImpl
import com.example.motorcyclegarage.ui.motorcycle.ui.MotorcycleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MotorcycleViewModel(repository = get())
    }
}

val repositoryModule = module {
    fun provideMotorcycleRepository(
        motorDao: MotorcycleDAO,
        context: Context
    ): MotorcycleRepository {

        return MotorcycleRepositoryImpl(motorDao, context)
    }
    println("TIAMM Modules Repository")
    single { provideMotorcycleRepository(get(), androidContext()) }
}
val roomDatabaseModule = module {
    single {
        Room.databaseBuilder(get(), MotorcycleDatabase::class.java, "MotorcycleDB")
            .createFromAsset("database/motorcycle_database.db").build()
    }
    single { get<MotorcycleDatabase>().motorcycleDAO() }
}