package com.example.bruno.kljvissenakenapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bruno.kljvissenakenapp.models.Lid

@Database(entities = [Lid::class],version = 1)
abstract class LidDatabase:RoomDatabase() {
    abstract fun lidDao (): LidDao

    companion object {
        @Volatile
        private var INSTANCE: LidDatabase? = null

        fun getInstance(context: Context):LidDatabase{
            val tempInstance = INSTANCE
            //Als er een instance vd database bestaat, return deze
            if ( tempInstance != null ) {
                return tempInstance
            }
            //De synchronized zorgt ervoor dat er maar 1 thread tegelijk de instance kan oproepen zodat er geen meerdere
            // instances aangemaakt kunnen worden.
            synchronized(this){
                val instance = Room.databaseBuilder (
                    context.applicationContext,
                    LidDatabase::class.java,
                    " lid_database "
                ). build ()

                INSTANCE = instance
                return instance

            }

        }
    }
}