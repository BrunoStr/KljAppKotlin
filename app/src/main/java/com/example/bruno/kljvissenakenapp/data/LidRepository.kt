package com.example.bruno.kljvissenakenapp.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bruno.kljvissenakenapp.models.Lid

class LidRepository(private val lidDao: LidDao) {

    val leden:LiveData<List<Lid>> = lidDao.getAll()


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

}