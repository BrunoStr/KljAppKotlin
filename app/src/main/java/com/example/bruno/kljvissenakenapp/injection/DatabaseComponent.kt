package com.example.bruno.kljvissenakenapp.injection

import com.example.bruno.kljvissenakenapp.App
import com.example.bruno.kljvissenakenapp.ui.LidViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun inject(app: App)
    fun inject(lidViewModel: LidViewModel)

}