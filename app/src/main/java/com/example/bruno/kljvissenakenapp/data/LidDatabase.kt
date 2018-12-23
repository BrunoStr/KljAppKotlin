package com.example.bruno.kljvissenakenapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bruno.kljvissenakenapp.models.Lid
import org.jetbrains.anko.doAsync

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
                    "Lid_database"
                ).addCallback(object : RoomDatabase.Callback(){
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                          doAsync {
                              populateDatabase(INSTANCE!!.lidDao())
                          }
                    }
                }).build()

                INSTANCE = instance
                return instance
            }

        }

        fun populateDatabase(lidDao: LidDao) {

            var lid = Lid(1,"Bruno",20.0,"Voor kerstmarkt")
            lidDao.insert(lid)
            lid = Lid(2,"Jan", 30.0,"Voor Kamp")
            lidDao.insert(lid)
        }
    }
}