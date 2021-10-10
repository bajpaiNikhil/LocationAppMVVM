package com.example.locationappmvvm.LocationViewMode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.locationappmvvm.Repositories.LocationRepository

class LocationViewModelFactory(
    private val repository: LocationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationViewModelIs(repository) as T
    }
}