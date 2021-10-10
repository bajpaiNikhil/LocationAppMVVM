package com.example.locationappmvvm.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "location_visited")
data  class Location_item(
    @ColumnInfo(name = "latitude")
    val latitudeIs : String,
    @ColumnInfo(name = "longitude")
    val longitudeIs : String,
    @ColumnInfo(name = "address")
    val addressIs : String
){
    @PrimaryKey(autoGenerate = true)
    var id : Int?= null
}