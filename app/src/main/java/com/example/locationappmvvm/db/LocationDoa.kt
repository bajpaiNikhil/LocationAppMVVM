package com.example.locationappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.locationappmvvm.db.entities.Location_item


@Dao
interface LocationDoa {

    @Insert
    suspend fun insert(item : Location_item)

    @Delete
    suspend fun delete(item: Location_item)

    @Query("SELECT * FROM location_visited")
    fun getAllLocationVisited(): LiveData<List<Location_item>>

}