package com.isaiasbueno.projeto_trademap_clone_app_dio.di

import com.isaiasbueno.projeto_trademap_clone_app_dio.AppDatabase
import com.isaiasbueno.projeto_trademap_clone_app_dio.MainViewModel
import com.isaiasbueno.projeto_trademap_clone_app_dio.RetrofitService
import com.isaiasbueno.projeto_trademap_clone_app_dio.repository.AcaoRepository
import com.isaiasbueno.projeto_trademap_clone_app_dio.ui.AcaoViewModel
import com.isaiasbueno.projeto_trademap_clone_app_dio.ui.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { AcaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.createService() }
}

val repositoryModule = module {
    single { AcaoRepository(get(), get()) }
}

val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).acaoDAO() }
}