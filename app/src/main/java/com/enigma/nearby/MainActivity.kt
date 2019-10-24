package com.enigma.nearby

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enigma.nearby.LiveData.NetworkStatusLiveData
import com.enigma.nearby.base.hide
import com.enigma.nearby.base.show
import com.enigma.nearby.data.cache.VenueItemEntity
import com.enigma.nearby.enums.ResponseType
import com.enigma.nearby.enums.SnackActionType
import com.enigma.nearby.viewmodel.MainActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PERMISSION_CODE = 100

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()
    private lateinit var locationManager: LocationManager
    private lateinit var placesAdapter: PlacesAdapter
    private var currentLocation: Location = Location("A")
    private var isRealTimeEnabled: Boolean = true
    private val networkStatusLiveData: NetworkStatusLiveData by inject()

    override fun onStart() {
        super.onStart()
        if (!checkLocationPermission()) {
            requestPermission()
        } else {
            viewModel.getLocationObserver()
            initLocationObserver()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        networkStatusLiveData.observe(this, Observer {
            if (it!=null && !it.isConnected) showSnackBar(
                getString(R.string.network_error),
                SnackActionType.NETWORKSETTING
            )

        })
        initView()
        progress.show()
        viewModel.retrieveDataFromCached()
        initObservers()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initLocationObserver()
            viewModel.getUpdatedRealtimeStatues()
        } else {
            showSnackBar(getString(R.string.enable_gps), SnackActionType.ASPERMISSIONNEED)
        }
    }


    private fun checkLocationPermission(): Boolean {
        if ((ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            return false
        } else {
            return true
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions( // method
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE
        )
    }


    fun initObservers() {
        viewModel.placesMutableLiveData.observe(this, Observer {
            handleFetchedData(it)
        })

        viewModel.responseTypeMutableLiveData.observe(this, Observer { type ->
            when (type) {
                ResponseType.SUCCESS -> {
                    viewModel.retrieveDataFromCached()
                }
                ResponseType.NETWORK -> {
                    showSnackBar(
                        getString(R.string.network_error),
                        SnackActionType.NETWORKSETTING
                    )
                }
                ResponseType.UNKNOWN -> {
                    showSnackBar(
                        getString(R.string.something_went_wrong),
                        SnackActionType.ASERQUESTFAIL
                    )
                }
            }
        })

        viewModel.getUpdatedRealtimeStatues().observe(this, Observer {
            isRealTimeEnabled = it
        })
    }

    private fun handleFetchedData(venues: List<VenueItemEntity?>?) {
        if (!venues.isNullOrEmpty()) {
            progress.hide()
            textViewNoData.hide()
            placesAdapter.places = venues.toMutableList()
        } else {
            progress.hide()
            textViewNoData.show()
        }
    }

    private fun initLocationObserver() {
        viewModel.getLocationObserver().observe(this, Observer {
            val tempLocation = it
            if (tempLocation.latitude != currentLocation.latitude && tempLocation.longitude != currentLocation.longitude) {
                currentLocation = tempLocation
                initRequest(currentLocation)
            }
        })
    }


    private fun showSnackBar(message: String, addAction: SnackActionType) {
        val snackBar = Snackbar.make(
            findViewById(
                R.id.myCoordinatorLayout
            ),
            message,
            Snackbar.LENGTH_LONG
        )
        when (addAction) {
            SnackActionType.ASERQUESTFAIL -> {
                snackBar.setAction(R.string.try_again, View.OnClickListener {
                    if (currentLocation != null) initRequest(currentLocation)
                })
            }
            SnackActionType.ASPERMISSIONNEED -> {
                snackBar.setAction(R.string.enable, View.OnClickListener {
                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                })
            }
            SnackActionType.NETWORKSETTING -> {
                snackBar.setAction(R.string.setting, View.OnClickListener {
                    startActivity(Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS))
                })
            }
        }
        snackBar.show()
    }

    private fun initRequest(currentLocation: Location) {
        viewModel.executeNearByPlace("${currentLocation.latitude},${currentLocation.longitude}")
    }

    private fun initView() {
        progress.visibility = View.VISIBLE
        placesAdapter = PlacesAdapter()
        placesRecyclerView.apply {
            adapter = placesAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (isRealTimeEnabled) {
            menu?.findItem(R.id.action_realTime)?.setVisible(false)
            menu?.findItem(R.id.action_singleTime)?.setVisible(true)
        } else {
            menu?.findItem(R.id.action_realTime)?.setVisible(true)
            menu?.findItem(R.id.action_singleTime)?.setVisible(false)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_realTime -> {
            viewModel.updateRealTimeOrNot(true)
            true
        }
        R.id.action_singleTime -> {
            viewModel.updateRealTimeOrNot(false)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
