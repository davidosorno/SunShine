package com.dog.sunshine.util

import com.dog.sunshine.data.networkservice.WeatherJsonObject
import com.dog.sunshine.data.weather.Weather

class JsonObjectToWeather {

    companion object{
        fun getDataFromJsonObject(json: WeatherJsonObject): Weather{
            return Weather(
                id = json.id,
                weatherId = json.id,
                max = json.main.temp_max.toInt(),
                min = json.main.temp_min.toInt(),
                humidity = json.main.humidity.toInt(),
                pressure = json.main.pressure.toInt(),
                wind = json.wind.speed.toInt(),
                location = json.wind.deg.toInt()
            )
        }
    }

}