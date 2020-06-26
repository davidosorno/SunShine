package com.dog.sunshine.util

import com.dog.sunshine.data.networkservice.CurrentWeatherJsonObject
import com.dog.sunshine.data.networkservice.CurrentWeatherMainData
import com.dog.sunshine.data.networkservice.Daily
import com.dog.sunshine.data.weather.CurrentWeather
import java.util.*
import kotlin.collections.ArrayList

class JsonObjectToWeather {

    companion object{
        fun getDataFromJsonObject(jsonCurrent: CurrentWeatherJsonObject, cityName: String): CurrentWeather {
            return CurrentWeather(
                weatherId = jsonCurrent.current.weather[0].id,
                temp = jsonCurrent.current.temp.toInt(),
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
                sunset = jsonCurrent.current.sunset,
                feelsLike = jsonCurrent.current.feels_like.toInt(),
                arrHourly = hourlyList(jsonCurrent.hourly, cityName),
                arrDaily = dailyList(jsonCurrent.daily, cityName).drop(1) //remove the first element because it is the Current weather that it already has been loaded
            )
        }

        private fun dailyList(daily: Array<Daily>, cityName: String): List<CurrentWeather> {
            val list = ArrayList<CurrentWeather>()
            var count = 0
            for(item in daily){
                count++
                list.add(
                    CurrentWeather(
                        date = Date(item.dt * 1000),
                        sunrise = item.sunrise,
                        sunset = item.sunset,
                        max = item.temp.max.toInt(),
                        min = item.temp.min.toInt(),
                        feelsLike = item.feels_like.day.toInt(),
                        humidity = item.humidity.toInt(),
                        pressure = item.pressure,
                        dewPoint = item.dew_point,
                        clouds = item.clouds,
                        uvi = item.uvi,
                        visibility = item.visibility,
                        wind = item.wind_speed.toInt(),
                        winddegrees = item.wind_deg.toInt(),
                        main = item.weather[0].main,
                        description = item.weather[0].description,
                        cityName = cityName
                    )
                )
                if(count == 6) break //I just take 6 days to show to the user
            }
            return list
        }

        private fun hourlyList(hourly: Array<CurrentWeatherMainData>, cityName: String): List<CurrentWeather> {
            val list = ArrayList<CurrentWeather>()
            for((count, item) in hourly.withIndex()){
                if(count > 0) //I do not want to take the same hour that current already has
                list.add(
                    CurrentWeather(
                        date = Date(item.dt * 1000),
                        sunrise = item.sunrise,
                        sunset = item.sunset,
                        temp = item.temp.toInt(),
                        feelsLike = item.feels_like.toInt(),
                        humidity = item.humidity.toInt(),
                        pressure = item.pressure,
                        dewPoint = item.dew_point,
                        clouds = item.clouds,
                        uvi = item.uvi,
                        visibility = item.visibility,
                        wind = item.wind_speed.toInt(),
                        winddegrees = item.wind_deg.toInt(),
                        main = item.weather[0].main,
                        description = item.weather[0].description,
                        cityName = cityName
                    )
                )
                if(count == 12) break //I just take 12 hours to show to the user
            }
            return list
        }
    }
}