package com.example.bruno.kljvissenakenapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bruno.kljvissenakenapp.models.Lid

@Database(entities = [Lid::class],version = 1)
abstract class LidDatabase:RoomDatabase() {

    abstract fun lidDao():LidDao

    companion object {
        @Volatile
        private var INSTANCE:LidDatabase? = null

        fun getInstance(context: Context):LidDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                //Als er reeds een instance is: return deze, anders: ga verder en maak een instance aan
                return tempInstance
            }
            //Synchronized betekent dat maar 1 thread tegelijk aan deze instance kan, zo kunnen er nooit meerdere instances aangemaakt worden
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LidDatabase::class.java,
                    "lid_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }

    }
}