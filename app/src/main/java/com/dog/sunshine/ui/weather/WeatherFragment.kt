package com.dog.sunshine.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
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
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.databinding.WeatherFragmentBinding
import com.dog.sunshine.util.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.weather_fragment.view.*

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: WeatherFragmentBinding
    private lateinit var adapter: WeatherAdapter
    private lateinit var gpsLocation: GPSLocation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment, container, false)

        val factory = WeatherFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        adapter = WeatherAdapter {
            onItemClick(it)
        }
        binding.root.recycler_list_weather.adapter = adapter

        viewModel.listWeather.observe(viewLifecycleOwner, Observer { listWeather ->
            listWeather?.let {
                adapter.submitList(listWeather)
                if(listWeather.size > 0 && viewModel.checkTodayLoaded(listWeather[0]!!.date)) {
                    binding.todayLayout.today = listWeather[0]!!
                    binding.root.pb_loading_indicator.visibility = View.INVISIBLE
                }else {
                    getLocationToGetWeather()
                }
            }?: run {
                binding.root.pb_loading_indicator.visibility = View.VISIBLE
            }
        })

        gpsLocation = GPSLocation(requireContext())
        gpsLocation.location.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(requireContext().isInternetAvailable()){
                    viewModel.getData(it)
                }else{
                    binding.root.showSnackBar(
                        binding.root.resources.getString(R.string.internet_required),
                        Snackbar.LENGTH_INDEFINITE,
                        R.string.ok
                    ) { closeApp() }
                }
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

    private fun onItemClick(weather: Weather) {
        findNavController().navigate(
            WeatherFragmentDirections.actionNavWeatherToDetailWeather(weather)
        )
    }

    override fun onResume() {
        super.onResume()
        getLocationToGetWeather()
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
                    PERMISSION_REQUEST_COARSE_LOCATION
                )
            }
        }else{
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                PERMISSION_REQUEST_COARSE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_REQUEST_COARSE_LOCATION){
            if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                canAccessLocation()
            }else{
                makePermissionRequest()
            }
        }
    }

    private fun canAccessLocation(): Boolean {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
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
            } else {
                return true
            }
        }
        return false
    }

    private fun getLocationToGetWeather() {
        if(canAccessLocation()){
            if(!gpsLocation.getLocationCoordinates()){
                binding.root.showSnackBar(
                    binding.root.resources.getString(R.string.permission_required),
                    Snackbar.LENGTH_INDEFINITE,
                    R.string.ok
                ) {}
            }
        }
    }
}