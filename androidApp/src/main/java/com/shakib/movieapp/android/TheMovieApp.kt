package com.shakib.movieapp.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.shakib.movieapp.android.ui.popular.PopularViewModel
import com.shakib.movieapp.di.initKoin
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class TheMovieApp: Application() {

    private val appModule = module {
        single<Context> { this@TheMovieApp }
        single<SharedPreferences> {
            get<Context>().getSharedPreferences("TheMovieApp", MODE_PRIVATE)
        }
    }

    private val viewModelModule = module {
        viewModel {
            PopularViewModel(get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin(appModule = appModule, viewModelsModule = viewModelModule)
    }
}
