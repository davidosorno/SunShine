package com.dog.sunshine.ui.weather

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.lifecycle.*
import com.dog.sunshine.R
import com.dog.sunshine.data.networkservice.WeatherApiService
import com.dog.sunshine.data.networkservice.CurrentWeatherJsonObject
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.data.weather.CurrentWeatherRepository
import com.dog.sunshine.util.JsonObjectToWeather
import com.dog.sunshine.util.canLoadTodayWeather
import kotlinx.coroutines.*
import java.util.*

class WeatherViewModel(
    private val currentWeatherRepository: CurrentWeatherRepository
): ViewModel() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val currentWeather = MediatorLiveData<CurrentWeather>()

    private val _showError = MutableLiveData<Int>()
    val showError: LiveData<Int>
        get() = _showError

    private val _canLoadTodayWeather = MutableLiveData<Boolean>()
    val canLoadTodayWeather: LiveData<Boolean>
        get() = _canLoadTodayWeather

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location

    private var cityName = ""

    init {
        currentWeather.addSource(currentWeatherRepository.getCurrentAndDaily(), currentWeather::setValue)
    }

    fun getData() {
        uiScope.launch {
            if(!getDataFromApi(cityName)) {
                _showError.value = R.string.error_loading_data
            }
        }
    }

    private suspend fun getDataFromApi(cityName: String):Boolean{
        var success: Long = 0
        withContext(Dispatchers.IO){
            val jsonObjectCurrent: CurrentWeatherJsonObject? = WeatherApiService.WeatherApi.getCurrentWeatherByGeoLocation(
                location.value!!.latitude.toFloat(),
                location.value!!.longitude.toFloat()
            )
            jsonObjectCurrent?.let {
                val currentWeather: CurrentWeather = JsonObjectToWeather.getDataFromJsonObject(jsonObjectCurrent, cityName)
                success = currentWeatherRepository.insert(currentWeather)
            }
        }
        return success > 0
    }


    fun cancelErrorMessage(){
        _showError.value = null
    }

    fun checkTodayLoaded(lastDateLoaded: Date?){
        _canLoadTodayWeather.value = canLoadTodayWeather(lastDateLoaded)
    }

    fun setLocation(loc: Location, context: Context){
        _location.value = loc
        val geocoder = Geocoder(
            context,
            Locale.US
        )
        val listAddress: List<Address> = geocoder.getFromLocation(
            location.value!!.latitude,
            location.value!!.longitude,
            1
        )
        cityName = listAddress[0].locality
//        if(currentWeather.value?.isNotEmpty()!!) {
            if (cityName == currentWeather.value?.cityName) {
                checkTodayLoaded(currentWeather.value?.date)
                return
            }
//        }
        checkTodayLoaded(null)
    }

    fun cancelLoadingData() {
        _canLoadTodayWeather.value = null
    }
}