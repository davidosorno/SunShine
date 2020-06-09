package com.dog.sunshine.ui.weather

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.data.weather.WeatherRepository

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    val listWeather = MediatorLiveData<PagedList<Weather>>()

    fun getData() {
        listWeather.addSource(weatherRepository.getWeatherList(), listWeather::setValue)
    }
}