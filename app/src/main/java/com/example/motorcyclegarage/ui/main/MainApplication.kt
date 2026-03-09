package com.example.motorcyclegarage.ui.main

import android.app.Application
import com.example.motorcyclegarage.common.logger.BaseLogger
import com.example.motorcyclegarage.common.logger.FactoryLogger
import com.example.motorcyclegarage.di.repositoryModule
import com.example.motorcyclegarage.di.roomDatabaseModule
import com.example.motorcyclegarage.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
private val logger: BaseLogger = FactoryLogger.getLoggerKClass(MainApplication::class)

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        logger.debug("onCreate()")
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
    }
}