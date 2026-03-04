package com.example.motorcyclegarage.main

import android.app.Application
import com.example.motorcyclegarage.di.roomDatabaseModule
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
//                apiModule,
//                networkModule,
//                repositoryModule,
//                viewModelModule,
                roomDatabaseModule
            )
        }
    }
}