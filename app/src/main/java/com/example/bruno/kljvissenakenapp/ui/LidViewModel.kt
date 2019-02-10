package com.example.bruno.kljvissenakenapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bruno.kljvissenakenapp.App
import com.example.bruno.kljvissenakenapp.data.LidRepository
import com.example.bruno.kljvissenakenapp.models.Lid
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class LidViewModel : ViewModel() {

    //De lidRepository wordt hier geinject door Dagger
    @Inject
    lateinit var lidRepository:LidRepository

    init {
        App.component.inject(this)
    }

    val leden: LiveData<List<Lid>> = lidRepository.leden


    fun insert(lid:Lid){
        doAsync {
            lidRepository.insert(lid)
        }
    }

    fun update(lid:Lid){
        doAsync {
            lidRepository.update(lid)
        }
    }

    fun delete(lid:Lid){
        doAsync {
            lidRepository.delete(lid)
        }
    }

    fun deleteAll(){
        doAsync {
            lidRepository.deleteAll()
        }
    }

    fun getAll(): LiveData<List<Lid>>{
        return leden
    }

}