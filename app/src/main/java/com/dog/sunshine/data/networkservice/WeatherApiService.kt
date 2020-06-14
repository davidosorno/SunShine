package com.dog.sunshine.data.networkservice

import com.dog.sunshine.util.API_URL
import com.dog.sunshine.util.ApiKey
import com.dog.sunshine.util.COORD_LAT
import com.dog.sunshine.util.COORD_LONG
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONObject
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

    @GET("weather")
    fun getMyLocationWeather(
        @Query(COORD_LAT) lat: Float,
        @Query(COORD_LONG) lon: Float,
        @Query("units") units: String = "metric",
        @Query("appid") api_key: String = ApiKey.key()
    ):Deferred<WeatherJsonObject>

    object WeatherApi {
        private val retrofitService : WeatherApiService by lazy {
            retrofit.create(WeatherApiService::class.java)
        }

        suspend fun getTodayWeather(lat: Float, lon: Float): WeatherJsonObject{
            return retrofitService.getMyLocationWeather(
                lat,
                lon
            ).await()
        }
    }
}