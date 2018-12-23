package com.example.bruno.kljvissenakenapp

import android.app.Application
import com.example.bruno.kljvissenakenapp.injection.DatabaseComponent
import com.example.bruno.kljvissenakenapp.injection.DaggerDatabaseComponent
import com.example.bruno.kljvissenakenapp.injection.DatabaseModule


class App: Application() {

    companion object {
        lateinit var component: DatabaseComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerDatabaseComponent
                        .builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}