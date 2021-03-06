package com.dog.sunshine.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.databinding.WeatherFragmentBinding
import com.dog.sunshine.ui.weather.daily.DailyWeatherAdapter
import com.dog.sunshine.ui.weather.hourly.WeatherHourlyAdapter
import com.dog.sunshine.util.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item_daily.view.*
import kotlinx.android.synthetic.main.list_item_hourly.view.*
import kotlinx.android.synthetic.main.weather_fragment.view.*

class WeatherFragment : Fragment() {

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var gpsLocation: GPSLocation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.weather_fragment,
            container,
            false
        )

        val factory = WeatherFactory(requireContext())
        val viewModel: WeatherViewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        binding.root.today_layout.setOnClickListener {todayView ->
            todayView?.let {
                viewModel.currentWeather.value?.let {
                    onItemClick(it)
                }
            }
        }

        viewModel.currentWeather.observe(viewLifecycleOwner, Observer { lastDateLoaded ->
            lastDateLoaded?.let {
                    binding.root.pb_loading_indicator.visibility = View.INVISIBLE
                    binding.todayLayout.constraintCurrentWeather.visibility = View.VISIBLE

                val dailyAdapter = DailyWeatherAdapter(
                        it.arrDaily
                    ){currentWeather ->
                        onItemClick(currentWeather)
                    }

                    binding.root.recycler_list_weather.adapter = dailyAdapter
                    binding.todayLayout.today = lastDateLoaded
                    viewModel.checkTodayLoaded(lastDateLoaded.date)

                    val hourlyAdapter = WeatherHourlyAdapter(it.arrHourly)
                    binding.root.list_weather_hourly.adapter = hourlyAdapter
                }
            })

        gpsLocation = GPSLocation(requireContext())
        gpsLocation.location.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(requireContext().isInternetAvailable()){
                    viewModel.setLocation(it, requireContext())
                }else{
                    binding.root.showSnackBar(
                        binding.root.resources.getString(R.string.internet_required),
                        Snackbar.LENGTH_INDEFINITE,
                        R.string.ok
                    ) { closeApp() }
                }
            }
        })

        viewModel.canLoadTodayWeather.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it && viewModel.location.value != null){
                    binding.root.pb_loading_indicator.visibility = View.VISIBLE
                    binding.todayLayout.constraintCurrentWeather.visibility = View.INVISIBLE
                    viewModel.getData()
                }
                viewModel.cancelLoadingData()
            }
        })

        viewModel.showError.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.root.showSnackBar(it)
                viewModel.cancelErrorMessage()
            }
        })
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun closeApp() {
        activity?.finish()
    }

    private fun onItemClick(currentWeather: CurrentWeather) {
        findNavController().navigate(
            WeatherFragmentDirections.actionNavWeatherToDetailWeather(currentWeather)
        )
    }


    override fun onStart() {
        super.onStart()
        binding.todayLayout.txtMeasurementUnits.text = when(MEASUREMENT_UNIT){
            METRIC -> "C"
            IMPERIAL -> "F"
            else -> ""
        }
        makePermissionRequest()
    }

    private fun makePermissionRequest() {
        if(shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            binding.root.showSnackBar(
                binding.root.resources.getString(R.string.permission_required),
                Snackbar.LENGTH_INDEFINITE,
                R.string.ok
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_REQUEST_LOCATION
                )
            }
        }else{
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSION_REQUEST_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_REQUEST_LOCATION){
            if (checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(
                    binding.root.context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                makePermissionRequest()
            } else if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationToGetWeather()
            }
        }
    }

    private fun getLocationToGetWeather() {
        if(!gpsLocation.getLocationCoordinates()){
            binding.root.showSnackBar(
                binding.root.resources.getString(R.string.permission_required),
                Snackbar.LENGTH_INDEFINITE,
                R.string.ok
            ) {}
        }
    }
}