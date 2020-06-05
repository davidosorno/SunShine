package com.dog.sunshine.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dog.sunshine.MainActivity
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.databinding.ItemWeatherBinding
import com.dog.sunshine.databinding.WeatherFragmentBinding
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.weather_fragment.view.*

class WeatherFragment : Fragment() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: WeatherFragmentBinding
    private lateinit var itemBinding: ItemWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weather_fragment, container, false)

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

        viewModel.listWeather.observe(viewLifecycleOwner, Observer {
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
}