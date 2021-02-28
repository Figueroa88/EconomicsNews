package com.alejandro.economicsnews.data.local

import com.alejandro.economicsnews.data.IDataSource
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.data.model.entity.UserEntity

interface ILocalDataSource : IDataSource
{
    suspend fun saveUser(newUser: UserEntity)

    suspend fun isUserHaveAccess(userValidate: UserEntity):String?

    suspend fun saveIndicator(newIndicator: IndicatorEntity)
}