package com.example.locationappmvvm.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.locationappmvvm.LocationViewMode.LocationViewModelIs
import com.example.locationappmvvm.R
import com.example.locationappmvvm.Repositories.LocationRepository
import com.example.locationappmvvm.LocationViewMode.LocationViewModelFactory
import com.example.locationappmvvm.db.LocationDatabase
import com.example.locationappmvvm.db.entities.Location_item
import com.example.locationappmvvm.others.LocationItemAdapter
import kotlinx.android.synthetic.main.activity_location.*

class LocationActivity : AppCompatActivity() , LocationListener {

    lateinit var viewModel : LocationViewModelIs

    var lattitude  = 0.00f
    var logitude   = 0.00f

    lateinit var lManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        val database = LocationDatabase(this)
        val repository = LocationRepository(database)
        val factory  = LocationViewModelFactory(repository)

        viewModel = ViewModelProvider(this , factory).get(LocationViewModelIs::class.java)

        val adapter = LocationItemAdapter(listOf() , viewModel)
        rvLocationitems.layoutManager = LinearLayoutManager(this)
        rvLocationitems.adapter = adapter

        viewModel.getAllLocationVisited().observe(this , Observer {
            adapter.item = it
            adapter.notifyDataSetChanged()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0 , 0,0,"ADD")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "ADD"->{
                permissionCheck()
                setuplocation()
                val item = Location_item(logitude.toString() , lattitude.toString() ,"ON")
                //val item  = Location_item(log lattitude , "ov")
                viewModel.insert(item)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setuplocation() {
        lManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        //tocheck for provider to get location
        val providerList = lManager.getProviders(true)

        if(providerList.isNotEmpty()){
            var providerName = ""
            if(providerList.contains(LocationManager.GPS_PROVIDER)){
                providerName = LocationManager.GPS_PROVIDER
            }else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
                providerName = LocationManager.NETWORK_PROVIDER
            }else{
                providerName = providerList[0]

            }

            Log.d("MainActivity","Provider name is $providerName")

            val loc = lManager.getLastKnownLocation(providerName)
            updateLocation(loc)
            lManager.requestLocationUpdates(providerName,3000L , 1.0f , this)
        }
    }

    private fun updateLocation(loc: Location?) {
        if (loc != null) {
            lattitude = loc.latitude.toFloat()
        }
        Log.d("MainActivity","longitude is ${loc?.latitude}")
        if (loc != null) {
            logitude = loc.longitude.toFloat()
        }
        Log.d("MainActivity","longitude is ${loc?.latitude },${loc?.longitude}")
    }


    private fun permissionCheck() {
        if( checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ||
            checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        else {
            Toast.makeText(this, "Location permission is available",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            1 -> {
                if( grantResults.isNotEmpty()){
                    if( grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Got permission granted..",
                            Toast.LENGTH_LONG).show()

                        return
                    }

                }

                finish()
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onLocationChanged(location: Location) {
        updateLocation(location)
    }

    override fun onDestroy() {
        super.onDestroy()
        lManager.removeUpdates(this)

    }

}
