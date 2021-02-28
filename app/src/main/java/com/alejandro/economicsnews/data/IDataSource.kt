package com.alejandro.economicsnews.data

import com.alejandro.economicsnews.data.model.IndicatorList

interface IDataSource
{
    suspend fun getAllNewsIndicator(): IndicatorList
}