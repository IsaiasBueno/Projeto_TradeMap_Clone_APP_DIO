package com.isaiasbueno.projeto_trademap_clone_app_dio

import android.app.Application
import com.isaiasbueno.projeto_trademap_clone_app_dio.di.daoModule
import com.isaiasbueno.projeto_trademap_clone_app_dio.di.repositoryModule
import com.isaiasbueno.projeto_trademap_clone_app_dio.di.serviceModule
import com.isaiasbueno.projeto_trademap_clone_app_dio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule, repositoryModule, daoModule)
        }
    }
}