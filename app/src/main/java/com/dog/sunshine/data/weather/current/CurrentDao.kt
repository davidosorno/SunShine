package com.dog.sunshine.data.weather.current

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dog.sunshine.data.BasicOperationsDao
import com.dog.sunshine.data.provider.WeatherContract

@Dao
interface CurrentDao: BasicOperationsDao<Current> {

    @Transaction
    @Query("SELECT * FROM current ORDER BY date DESC")
    fun getAll(): DataSource.Factory<Int, Current>

    @Transaction
    @Query("SELECT * FROM current")
    fun getCurrentAndDaily(): LiveData<Current>



    @Transaction
    @Query("SELECT * FROM current WHERE ${WeatherContract.COLUMN_DATE} = :date")
    fun getByDate(date: Long): LiveData<List<Current>>


//    CURSOR FOR CONTENT PROVIDER

    @Transaction
    @Query("SELECT * FROM current ORDER BY date DESC")
    fun getAllInCursor(): Cursor

    @Transaction
    @Query("SELECT * FROM current WHERE ${WeatherContract.COLUMN_DATE} = :date")
    fun getByDateInCursor(date: Long): Cursor
}