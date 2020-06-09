package com.dog.sunshine.ui.weather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.databinding.ItemWeatherBinding
import com.dog.sunshine.databinding.WeatherFragmentBinding
import com.dog.sunshine.util.showSnackBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item.view.*


const val PERMISSION_REQUEST_BACKGROUND_LOCATION = 0

class WeatherFragment : Fragment() {


    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: WeatherFragmentBinding
    private lateinit var itemBinding: ItemWeatherBinding
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.weather_fragment, container, false)

        val factory = WeatherFactory(requireContext())
        viewModel = ViewModelProvider(this, factory)[WeatherViewModel::class.java]

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.weather_fragment,
            container,
            false
        )

        itemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_weather,
            container,
            false
        )

        val adapter = WeatherAdapter {
            onItemClick(it)
        }
        root.recycler_list_weather.adapter = adapter

        viewModel.listWeather?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return root
    }

    private fun onItemClick(weather: Weather) {
        findNavController().navigate(
            WeatherFragmentDirections.actionNavWeatherToDetailWeather(weather)
        )
    }

    override fun onStart() {
        super.onStart()
        canAccessLocation()
    }

    private fun makePermissionRequest() {
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_BACKGROUND_LOCATION)){
            root.showSnackBar(
                resources.getString(R.string.permission_required),
                Snackbar.LENGTH_INDEFINITE,
                R.string.ok
            ){
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                    PERMISSION_REQUEST_BACKGROUND_LOCATION
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_REQUEST_BACKGROUND_LOCATION){
            if(grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                canAccessLocation()
            }else{
                makePermissionRequest()
            }
        }
    }

    private fun canAccessLocation() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            if(ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
                viewModel.getData()
            }else{
                makePermissionRequest()
            }
        }else{
            root.showSnackBar(R.string.permission_required)
        }
    }
}