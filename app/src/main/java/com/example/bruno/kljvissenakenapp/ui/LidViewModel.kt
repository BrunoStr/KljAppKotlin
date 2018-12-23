package com.example.bruno.kljvissenakenapp.ui

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bruno.kljvissenakenapp.data.LidRepository
import com.example.bruno.kljvissenakenapp.models.Lid

class LidViewModel(@NonNull application: Application,
                   private val lidRepository: LidRepository = LidRepository(application),
                   private val leden: LiveData<List<Lid>> = lidRepository.getAll()): AndroidViewModel(application) {

    fun insert(lid:Lid){
        lidRepository.insert(lid)
    }

    fun update(lid:Lid){
        lidRepository.update(lid)
    }

    fun delete(lid:Lid){
        lidRepository.delete(lid)
    }

    fun deleteAll(){
        lidRepository.deleteAll()
    }

    fun getAll(): LiveData<List<Lid>>{
        return leden
    }

}