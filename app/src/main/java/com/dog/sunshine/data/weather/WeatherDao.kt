package com.dog.sunshine.data.weather

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dog.sunshine.data.provider.WeatherContract

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Weather): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg obj: Weather)

    @Transaction
    @Query("SELECT * FROM weather")
    fun getAll(): DataSource.Factory<Int, Weather>

    @Transaction
    @Query("SELECT * FROM weather WHERE ${WeatherContract.COLUMN_DATE} = :date")
    fun getByDate(date: Long): LiveData<List<Weather>>
}