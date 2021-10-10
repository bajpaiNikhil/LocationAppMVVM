package com.example.locationappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.locationappmvvm.db.entities.Location_item


@Database(
    entities = [Location_item::class],
    version = 1
)
abstract class LocationDatabase : RoomDatabase() {

    abstract  fun getLocationDoa() : LocationDoa

    companion object{

        @Volatile
        private var instance : LocationDatabase? = null
        private var Lock  =  Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext ,
            LocationDatabase::class.java ,
        "LocationDB.db").build()
    }
}