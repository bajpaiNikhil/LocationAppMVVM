package com.example.locationappmvvm.LocationViewMode

import androidx.lifecycle.ViewModel
import com.example.locationappmvvm.Repositories.LocationRepository
import com.example.locationappmvvm.db.entities.Location_item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModelIs(
    private var repository: LocationRepository
)  : ViewModel(){

    fun insert(item: Location_item) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(item)
    }
    fun delete(item: Location_item) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }
    fun getAllLocationVisited() = repository.getAllLocationVisited()
}