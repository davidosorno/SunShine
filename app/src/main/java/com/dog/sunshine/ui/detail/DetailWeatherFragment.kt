package com.dog.sunshine.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.databinding.FragmentDetailWeatherBinding
import com.dog.sunshine.util.WEATHER_TABLE_NAME


class DetailWeatherFragment : Fragment() {

    lateinit var binding: FragmentDetailWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail_weather,
            container,
            false
        )
        val weather: CurrentWeather? = arguments?.getParcelable(WEATHER_TABLE_NAME)
        weather?.let {
            binding.todayLayout.today = weather
            binding.detailLayout.today = weather
            binding.todayLayout.constraintCurrentWeather.visibility = View.VISIBLE
        }

        binding.imgBack?.setOnClickListener {
            closeFragmentDetails()
        }
        return binding.root
    }

    private fun closeFragmentDetails() {
        requireActivity().onBackPressed()
    }
}