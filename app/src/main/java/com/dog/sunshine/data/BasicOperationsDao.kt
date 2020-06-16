package com.dog.sunshine.data

import androidx.room.*

@Dao
interface BasicOperationsDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg obj: T)
}