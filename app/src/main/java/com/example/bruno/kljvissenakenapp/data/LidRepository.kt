package com.example.bruno.kljvissenakenapp.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bruno.kljvissenakenapp.models.Lid

class LidRepository {
    val lidDao:LidDao
    val leden:LiveData<List<Lid>>


    constructor(application: Application){
        val lidDatabase = LidDatabase.getInstance(application)
        lidDao = lidDatabase.lidDao()
        leden = lidDao.getAll()
    }

    @WorkerThread
    fun insert(lid:Lid){
        lidDao.insert(lid)
    }

    @WorkerThread
    fun update(lid:Lid){
        lidDao.update(lid)
    }

    @WorkerThread
    fun delete(lid:Lid){
        lidDao.delete(lid)
    }

    @WorkerThread
    fun deleteAll(){
        lidDao.deleteAll()
    }

    @WorkerThread
    fun getAll():LiveData<List<Lid>>{
       return lidDao.getAll()
    }
}