package com.alejandro.economicsnews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity

@Dao
interface IIndicatorDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: IndicatorEntity)

    @Query("SELECT * FROM indicatorentity")
    fun getAll(): MutableList<IndicatorEntity>
}