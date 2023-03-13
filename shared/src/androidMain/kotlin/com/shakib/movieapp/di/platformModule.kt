package com.shakib.movieapp.di

import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

actual val platformModule = module {
    single<Settings> {
        SharedPreferencesSettings(get())
    }
}
