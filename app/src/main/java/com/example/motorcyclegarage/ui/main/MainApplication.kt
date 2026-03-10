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

/* MainApplication lives throughout the Application lifecycle and with Koin dependency injection
* modules instantiates the the business logic classes and services like viewmodel, repository
* and the database for example and is registered in AndroidManifest.xml
* */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        logger.debug("onCreate()")
        startKoin {// Start Dependency Injection using Koin
            androidLogger()// Setup Android Logger for Koin
            androidContext(this@MainApplication) //Add Context instance to Koin container
            modules(// Load definitions from modules
                repositoryModule,
                viewModelModule,
                roomDatabaseModule
            )
        }
    }
}