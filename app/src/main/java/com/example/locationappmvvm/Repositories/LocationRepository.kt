package com.example.locationappmvvm.Repositories

import com.example.locationappmvvm.db.LocationDatabase
import com.example.locationappmvvm.db.entities.Location_item

class LocationRepository(
    private var db : LocationDatabase
) {
    suspend fun insert(item: Location_item) = db.getLocationDoa().insert(item)
    suspend fun delete(item: Location_item) = db.getLocationDoa().delete(item)

    fun getAllLocationVisited() = db.getLocationDoa().getAllLocationVisited()

}