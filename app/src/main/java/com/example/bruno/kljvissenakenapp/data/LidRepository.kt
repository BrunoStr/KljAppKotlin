package com.example.bruno.kljvissenakenapp.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bruno.kljvissenakenapp.models.Lid

class LidRepository(private val lidDao: LidDao) {
    val leden:LiveData<List<Lid>> = lidDao.getAll()

    //WorkerThread toevoegen zodat de database tasks async gebeuren en dus de main thread niet blocken
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
    fun getAll(){
        lidDao.getAll()
    }
}