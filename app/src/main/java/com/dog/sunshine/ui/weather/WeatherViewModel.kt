package com.dog.sunshine.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.data.weather.WeatherRepository

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    val listWeather: LiveData<PagedList<Weather>> = weatherRepository.getWeatherList()

}