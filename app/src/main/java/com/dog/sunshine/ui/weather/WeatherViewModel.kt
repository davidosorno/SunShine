package com.dog.sunshine.ui.weather

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dog.sunshine.R
import com.dog.sunshine.data.networkservice.WeatherApiService
import com.dog.sunshine.data.networkservice.CurrentWeatherJsonObject
import com.dog.sunshine.data.weather.current.Current
import com.dog.sunshine.data.weather.current.CurrentRepository
import com.dog.sunshine.util.JsonObjectToWeather
import com.dog.sunshine.util.canLoadTodayWeather
import kotlinx.coroutines.*
import java.util.*

class WeatherViewModel(
    private val currentRepository: CurrentRepository
): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val listWeather = MediatorLiveData<PagedList<Current>>()

    private val _showError = MutableLiveData<Int>()
    val showError: LiveData<Int>
        get() = _showError

    private val _canLoadTodayWeather = MutableLiveData<Boolean>()
    val canLoadTodayWeather: LiveData<Boolean>
        get() = _canLoadTodayWeather

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location

    init {
        listWeather.addSource(currentRepository.getWeatherList(), listWeather::setValue)
    }

    fun getData(context: Context) {
        val geocoder = Geocoder(
            context,
            Locale.US
        )
        val listAddress: List<Address> = geocoder.getFromLocation(
            location.value!!.latitude,
            location.value!!.longitude,
            1
        )
        uiScope.launch {
            if(listAddress.isNotEmpty()){
                if(!getDataFromApi(listAddress[0].locality)) {
                    _showError.value = R.string.error_loading_data
                }
            }
        }
    }

    private suspend fun getDataFromApi(cityName: String):Boolean{
        var success: Long = 0
        withContext(Dispatchers.IO){
            val jsonObjectCurrent: CurrentWeatherJsonObject = WeatherApiService.WeatherApi.getCurrentWeatherByGeoLocation(
                location.value!!.latitude.toFloat(),
                location.value!!.longitude.toFloat()
            )
            val current: Current = JsonObjectToWeather.getDataFromJsonObject(jsonObjectCurrent, cityName)
            success = currentRepository.insert(current)
        }
        return success > 0
    }


    fun cancelErrorMessage(){
        _showError.value = null
    }

    fun checkTodayLoaded(lastDateLoaded: Date?){
        _canLoadTodayWeather.value = canLoadTodayWeather(lastDateLoaded)
    }

    fun setLocation(location: Location){
        _location.value = location
        checkTodayLoaded(
            if(listWeather.value?.isNotEmpty()!!) {
                listWeather.value?.get(0)?.date
            }
            else
                null
        )
    }

    fun cancelLoadingData() {
        _canLoadTodayWeather.value = null
    }
}