package com.example.weatherapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapplication.databinding.ActivityMainBinding
import com.example.weatherapplication.model.WeatherDetailsModel
import com.example.weatherapplication.model.WeatherDetailsResponseModel
import com.example.weatherapplication.utils.DateUtilities
import com.example.weatherapplication.utils.NetworkUtility
import com.example.weatherapplication.utils.RetrofitModule
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var binding: ActivityMainBinding
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var todayWeatherFragment: TodayWeatherFragment
    private lateinit var comingDaysWeatherFragment: ComingDaysWeatherFragment
    private var weatherDetailsResponseModel: WeatherDetailsResponseModel? = null
    private val weathersList: MutableList<WeatherDetailsModel> = ArrayList()
    private var currentDayWeatherDetails: WeatherDetailsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        checkLocationPermission()
    }

    private fun createTabs() {
        weatherDetailsResponseModel?.data?.forEach {
            val calendar = DateUtilities.getCalendarFromDateString(it.datetime)
            it.calendar = calendar
            weathersList.add(it)
        }

        if(weathersList.isNotEmpty()) {
            currentDayWeatherDetails = weathersList[0]
            weathersList.removeAt(0)
        } else {
            currentDayWeatherDetails = WeatherDetailsModel()
        }
        todayWeatherFragment =
            TodayWeatherFragment.getInstance(currentDayWeatherDetails)
        comingDaysWeatherFragment =
            ComingDaysWeatherFragment.getInstance(weathersList)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.today)))
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(getString(R.string.next_fifteen_days))
        )

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                setCurrentTabFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        setCurrentTabFragment(0)
    }

    private fun setCurrentTabFragment(position: Int) {
        val fragment = if (position == 0) {
            todayWeatherFragment
        } else {
            comingDaysWeatherFragment
        }
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    private val permReqLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }
            if (granted) {
                getCurrentLocation()
            }
        }

    private fun checkLocationPermission() {
        if (hasPermissions(this, permissions)) {
            checkLocationManagerLocation()
        } else {
            permReqLauncher.launch(
                permissions
            )
        }
    }

    // util method
    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean =
        permissions.all {
            ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

    private fun checkLocationManagerLocation() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGps()
        } else {
            getCurrentLocation()
        }
    }

    private fun onGps() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.enable_gps)).setCancelable(false).setPositiveButton(
            getString(R.string.yes)
        ) { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                getString(R.string.no)
            ) { dialog, _ -> dialog.cancel() }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        locationGPS?.let {
            latitude = it.latitude
            longitude = it.longitude
        }

        getWeatherDetails()
    }

    private fun getWeatherDetails() {
        if (NetworkUtility.isNetworkConnected(this)) {
            val loadingDialog = CustomProgressDialog.progressDialog(this)
            loadingDialog.show()
            val retrofitModule = RetrofitModule.getInstance()
            val apiService = retrofitModule.providesRetrofit().create(ApiService::class.java)
            val call = apiService.getWeatherDetailsList(latitude, longitude)
            call.enqueue(object : Callback<WeatherDetailsResponseModel> {
                override fun onResponse(
                    call: Call<WeatherDetailsResponseModel>,
                    response: Response<WeatherDetailsResponseModel>
                ) {
                    loadingDialog.dismiss()
                    if (response.isSuccessful) {
                        weatherDetailsResponseModel = response.body()
                    } else {
                        showErrorMessage(response.message())
                    }
                    createTabs()
                }

                override fun onFailure(call: Call<WeatherDetailsResponseModel>, t: Throwable) {
                    loadingDialog.dismiss()
                    showErrorMessage(t.message)
                }

            })
        } else {
            showErrorMessage(getString(R.string.internet_not_available))
        }
    }

    companion object {
        var permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun showErrorMessage(message: String?) {
        if (!isFinishing) {
            val lBuilder =
                AlertDialog.Builder(
                    this,
                )
            lBuilder.setTitle(getString(R.string.dialog_message))

            lBuilder.setCancelable(false)
            lBuilder.setPositiveButton(getString(R.string.dialog_ok), null)

            val dialog = lBuilder.create()
            dialog.setCanceledOnTouchOutside(false)
            dialog.show()
            dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}