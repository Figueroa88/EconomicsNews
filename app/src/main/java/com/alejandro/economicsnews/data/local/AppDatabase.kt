package com.alejandro.economicsnews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alejandro.economicsnews.data.local.dao.IIndicatorDao
import com.alejandro.economicsnews.data.local.dao.IUserDao
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.data.model.entity.UserEntity

@Database(entities = [IndicatorEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    //Dao

    abstract fun userDao(): IUserDao
    abstract fun indicatorDao(): IIndicatorDao

    //Statics

    companion object
    {
        @JvmStatic
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase
        {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "economic_table"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance()
        {
            INSTANCE = null
        }
    }
}