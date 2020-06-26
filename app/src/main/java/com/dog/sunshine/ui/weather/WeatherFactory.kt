package com.dog.sunshine.ui.weather

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dog.sunshine.data.weather.CurrentWeatherRepository
import java.lang.IllegalArgumentException

class WeatherFactory(val context: Context): ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                CurrentWeatherRepository.getInstance(context)!!
            ) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }

}