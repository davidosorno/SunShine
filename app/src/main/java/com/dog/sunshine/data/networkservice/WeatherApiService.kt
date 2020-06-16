package com.dog.sunshine.data.networkservice

import com.dog.sunshine.data.provider.WeatherContract.Companion.COORD_LAT
import com.dog.sunshine.data.provider.WeatherContract.Companion.COORD_LONG
import com.dog.sunshine.util.API_URL
import com.dog.sunshine.util.ApiKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(API_URL)
    .build()

interface WeatherApiService {

    object WeatherApi {
        private val retrofitService : WeatherApiService by lazy {
            retrofit.create(WeatherApiService::class.java)
        }

        suspend fun getCurrentWeatherByGeoLocation(lat: Float, lon: Float): CurrentWeatherJsonObject{
            return retrofitService.getCurrentWeatherByGeoLocation(
                lat,
                lon
            ).await()
        }

//        suspend fun getCurrentWeatherByCity(city: String): WeatherJsonObject{
//            return retrofitService.getCurrentWeatherByCity(
//                city
//            ).await()
//        }
//
//        suspend fun getCurrentWeatherByIdCity(idCity: Long): WeatherJsonObject{
//            return retrofitService.getCurrentWeatherByIdCity(
//                idCity
//            ).await()
//        }
    }

    @GET("onecall")
    fun getCurrentWeatherByGeoLocation(
        @Query(COORD_LAT) lat: Float,
        @Query(COORD_LONG) lon: Float,
        @Query("units") units: String = "metric",
        @Query("exclude") exclude: String = "minutely",
        @Query("appid") api_key: String = ApiKey.key()
    ):Deferred<CurrentWeatherJsonObject>


//    @GET("onecall")
//    fun getCurrentWeatherByCity(
//        @Query("q") city: String,
//        @Query("units") units: String = "metric",
//        @Query("exclude") exclude: String = "minutely",
//        @Query("appid") api_key: String = ApiKey.key()
//    ):Deferred<WeatherJsonObject>
//
//
//    @GET("weather")
//    fun getCurrentWeatherByIdCity(
//        @Query("id") idCity: Long,
//        @Query("units") units: String = "metric",
//        @Query("exclude") exclude: String = "minutely",
//        @Query("appid") api_key: String = ApiKey.key()
//    ):Deferred<WeatherJsonObject>
}