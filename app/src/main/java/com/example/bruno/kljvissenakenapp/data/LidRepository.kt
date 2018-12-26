package com.example.bruno.kljvissenakenapp.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bruno.kljvissenakenapp.models.Lid

class LidRepository(private val lidDao: LidDao) {

    val leden:LiveData<List<Lid>> = lidDao.getAll()


    /**
     * Voeg nieuw lid toe aan Lid_Database
     */
    @WorkerThread
    fun insert(lid:Lid){
        lidDao.insert(lid)
    }

    /**
     * Werk gegevens van bestaande lid bij in de Lid_Database
     */
    @WorkerThread
    fun update(lid:Lid){
        lidDao.update(lid)
    }

    /**
     * Verwijder lid uit Lid_Database
     */
    @WorkerThread
    fun delete(lid:Lid){
        lidDao.delete(lid)
    }

    /**
     * Verwijder alle leden uit Lid_Database
     */
    @WorkerThread
    fun deleteAll(){
        lidDao.deleteAll()
    }

}