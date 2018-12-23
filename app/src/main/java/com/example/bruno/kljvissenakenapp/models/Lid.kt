package com.example.bruno.kljvissenakenapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lid_table")
class Lid(@PrimaryKey(autoGenerate = true) val id:Int, val naam:String, val teBetalen:Double, val omschrijving:String) {
}