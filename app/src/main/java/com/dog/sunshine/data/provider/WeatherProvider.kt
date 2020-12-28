package com.dog.sunshine.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dog.sunshine.data.WeatherDB
import com.dog.sunshine.data.provider.WeatherContract.Companion.AUTHORITY
import com.dog.sunshine.data.weather.CurrentWeather
import com.dog.sunshine.data.weather.CurrentWeatherDao
import com.dog.sunshine.util.WEATHER_TABLE_NAME

class WeatherProvider: ContentProvider() {

//    define access to database
    private lateinit var currentWeatherDao: CurrentWeatherDao

    /*
     * These constant will be used to match URIs with the data they are looking for. We will take
     * advantage of the UriMatcher class to make that matching MUCH easier than doing something
     * ourselves, such as using regular expressions.
     */
    private val CODE_WEATHER = 100
    private val CODE_WEATHER_WITH_DATE = 101

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        // This URI is content://com.dog.sunshine/weather/
        addURI(AUTHORITY, WEATHER_TABLE_NAME, CODE_WEATHER)

        // This URI is content://com.dog.sunshine/weather/1472214172
        addURI(AUTHORITY, "$WEATHER_TABLE_NAME/#", CODE_WEATHER_WITH_DATE)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun bulkInsert(uri: Uri, values: Array<out ContentValues>): Int {
        val currentWeatherValues: ArrayList<CurrentWeather> = ArrayList()
        currentWeatherDao = WeatherDB.getInstance(context!!).weatherDao()

        //TODO create the good way to access by content provider
        when(sUriMatcher.match(uri)) {
            CODE_WEATHER -> {
                for (v: ContentValues in values) {
//                    weatherValues.add(v.getStructure(v))
                }
                currentWeatherDao.insertAll(*currentWeatherValues.toTypedArray())
            }

            CODE_WEATHER_WITH_DATE -> {

            }
        }
        return currentWeatherValues.size
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when(sUriMatcher.match(uri)){
            CODE_WEATHER -> {
                cursor = currentWeatherDao.getAllInCursor()
            }

            CODE_WEATHER_WITH_DATE -> {
                cursor = currentWeatherDao.getByDateInCursor(ContentUris.parseId(uri))
            }
        }
        cursor?.setNotificationUri(context!!.contentResolver!!, uri)
        return cursor
    }

//    fun query(uri: Uri): LiveData<List<Weather>>{
//        database =
//            WeatherDB.getInstance(context!!)
//        weatherDao = database.weatherDao()
//        return when(sUriMatcher.match(uri)){
//            CODE_WEATHER -> {
//                weatherDao.getByDate(ContentUris.parseId(uri))
//            }
//
//    //            CODE_WEATHER_WITH_DATE -> {
//    //                return weatherDao.getAll()
//    //            }
//    //
//            else -> weatherDao.getAll()
//        }
//    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }
}