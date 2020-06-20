package com.dog.sunshine.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dog.sunshine.R
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.databinding.FragmentDetailWeatherBinding
import com.dog.sunshine.util.WEATHER_TABLE_NAME
import kotlinx.android.synthetic.main.weather_fragment.view.today_layout


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
        val weather: Current = arguments?.getParcelable(WEATHER_TABLE_NAME)!!
        binding.todayLayout.today = weather
        binding.detailLayout.today = weather
        return binding.root
    }
}