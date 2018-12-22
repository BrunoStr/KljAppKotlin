package com.example.bruno.kljvissenakenapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bruno.kljvissenakenapp.models.Lid

@Dao
interface LidDao {

    @Insert
    fun insert(lid:Lid)

    @Update
    fun update(lid:Lid)

    @Delete
    fun delete(lid:Lid)

    //Voor deze operatie is er geen default annotation dus deze moeten we zelf maken
    @Query("DELETE FROM lid_table")
    fun deleteAll()

    @Query("SELECT * FROM lid_table")
    fun getAll():LiveData<List<Lid>>

}
