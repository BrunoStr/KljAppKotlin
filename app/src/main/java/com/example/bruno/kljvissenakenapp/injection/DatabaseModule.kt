package com.example.bruno.kljvissenakenapp.injection

import android.app.Application
import android.content.Context
import com.example.bruno.kljvissenakenapp.data.LidDao
import com.example.bruno.kljvissenakenapp.data.LidDatabase
import com.example.bruno.kljvissenakenapp.data.LidRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideLidRepository(lidDao: LidDao): LidRepository {
        return LidRepository(lidDao)
    }

    @Provides
    @Singleton
    internal fun provideLidDao(lidDatabase: LidDatabase): LidDao {
        return lidDatabase.lidDao()
    }


    @Provides
    @Singleton
    internal fun provideLidDatabase(context: Context): LidDatabase {
        return LidDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

}