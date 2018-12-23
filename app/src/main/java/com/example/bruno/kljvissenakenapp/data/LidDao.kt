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

    //Wanneer we iets anders willen doen dan de default operations maken we gebruik van Query()

    @Query("DELETE FROM lid_table")
    fun deleteAll()

    //Door de liveDate kunnen we observen
    @Query("SELECT * FROM lid_table ORDER BY naam ASC")
    fun getAll():LiveData<List<Lid>>
}