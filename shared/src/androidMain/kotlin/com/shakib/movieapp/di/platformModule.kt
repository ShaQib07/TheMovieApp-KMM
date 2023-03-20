package com.shakib.movieapp.di

import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import com.shakib.movieapp.MovieDb
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.koin.dsl.module

actual val platformModule = module {
    single<Settings> {
        SharedPreferencesSettings(get())
    }
    single<SqlDriver> {
        AndroidSqliteDriver(MovieDb.Schema, get(), "MovieDb")
    }
}
