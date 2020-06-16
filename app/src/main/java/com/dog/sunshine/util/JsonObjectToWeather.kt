package com.dog.sunshine.util

import com.dog.sunshine.data.Converters
import com.dog.sunshine.data.networkservice.CurrentWeatherJsonObject
import com.dog.sunshine.data.weather.current.Current
import java.util.*

class JsonObjectToWeather {

    companion object{
        fun getDataFromJsonObject(jsonCurrent: CurrentWeatherJsonObject, cityName: String): Current {
            val dayOfYear = setFormatDate(Date(), "D").toInt()
            val n = 9
            return Current(
                id = dayOfYear,
                weatherId = jsonCurrent.current.weather[0].id,
                max = jsonCurrent.daily[0].temp.max.toInt(),
                min = jsonCurrent.daily[0].temp.min.toInt(),
                humidity = jsonCurrent.current.humidity.toInt(),
                pressure = jsonCurrent.current.pressure,
                wind = jsonCurrent.current.wind_speed.toInt(),
                winddegrees = jsonCurrent.current.wind_deg.toInt(),
                icon = jsonCurrent.current.weather[0].icon,
                main = jsonCurrent.current.weather[0].main,
                description = jsonCurrent.current.weather[0].description,
                cityName = cityName,
                sunrise = jsonCurrent.current.sunrise,
                sunset = jsonCurrent.current.sunset
            )
        }
    }

}