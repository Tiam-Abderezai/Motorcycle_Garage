package com.example.motorcyclegarage.ui.main

import android.app.Application
import com.example.motorcyclegarage.di.repositoryModule
import com.example.motorcyclegarage.di.roomDatabaseModule
import com.example.motorcyclegarage.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Dependency Injection using koin
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                repositoryModule,
                viewModelModule,
                roomDatabaseModule
            )
        }
        println("TIAMM MainApplication")
    }
}