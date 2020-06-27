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
                //remove the first element because it is the hourly weather that it already has been loaded and I just want to take 12 hours but the query has 48 hours
                arrHourly = hourlyList(jsonCurrent.hourly, cityName).drop(1).dropLast(35),
                //remove the first element because it is the Current weather that it already has been loaded and I just want to take 6 days
                arrDaily = dailyList(jsonCurrent.daily, cityName).drop(1).dropLast(1)
            )
        }

        private fun dailyList(daily: Array<Daily>, cityName: String): List<CurrentWeather> {
            val list = ArrayList<CurrentWeather>()
            for(item in daily){
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
                        icon = item.weather[0].icon,
                        visibility = item.visibility,
                        wind = item.wind_speed.toInt(),
                        winddegrees = item.wind_deg.toInt(),
                        main = item.weather[0].main,
                        description = item.weather[0].description,
                        cityName = cityName
                    )
                )
            }
            return list
        }

        private fun hourlyList(hourly: Array<CurrentWeatherMainData>, cityName: String): List<CurrentWeather> {
            val list = ArrayList<CurrentWeather>()
            for(item in hourly){
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
                        icon = item.weather[0].icon,
                        description = item.weather[0].description,
                        cityName = cityName
                    )
                )
            }
            return list
        }
    }
}