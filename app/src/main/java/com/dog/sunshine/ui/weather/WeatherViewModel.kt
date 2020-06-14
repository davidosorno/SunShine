package com.dog.sunshine.ui.weather

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dog.sunshine.R
import com.dog.sunshine.data.networkservice.WeatherApiService
import com.dog.sunshine.data.networkservice.WeatherJsonObject
import com.dog.sunshine.data.weather.Weather
import com.dog.sunshine.data.weather.WeatherRepository
import com.dog.sunshine.util.JsonObjectToWeather
import com.dog.sunshine.util.isTodayLoaded
import kotlinx.coroutines.*
import java.util.*

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val listWeather = MediatorLiveData<PagedList<Weather>>()

    private val _todayWeather = MutableLiveData<Weather>()
    val todayWeather: LiveData<Weather> = _todayWeather

    private val _showError = MutableLiveData<Int>()
    val showError: LiveData<Int>
        get() = _showError

    private var isTodayWeatherLoaded = true

    init {
        listWeather.addSource(weatherRepository.getWeatherList(), listWeather::setValue)
    }

    fun getData(location: Location) {
        if(!isTodayWeatherLoaded) {
            uiScope.launch {
                if (!getDataFromApi(location)) {
                    _showError.value = R.string.error_loading_data
                }
            }
        }
    }

    private suspend fun getDataFromApi(location: Location):Boolean{
        var success: Long = 0
        withContext(Dispatchers.IO){
            val jsonObject:WeatherJsonObject = WeatherApiService.WeatherApi.getTodayWeather(
                location.latitude.toFloat(),
                location.longitude.toFloat()
            )
            val weather: Weather = JsonObjectToWeather.getDataFromJsonObject(jsonObject)
            success = weatherRepository.insert(weather)
        }
        return success > 0
    }


    fun cancelErrorMessage(){
        _showError.value = null
    }

    fun checkTodayLoaded(lastDateLoaded: Date): Boolean{
        isTodayWeatherLoaded = isTodayLoaded(lastDateLoaded)
        return isTodayWeatherLoaded
    }
}