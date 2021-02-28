package com.alejandro.economicsnews.data.remote

import com.alejandro.economicsnews.data.IDataSource
import com.alejandro.economicsnews.data.model.IndicatorList
import com.alejandro.economicsnews.data.model.response.toIndicatorList
import com.alejandro.economicsnews.repository.IWebService

class RemoteDataSource(private val mWebService: IWebService) : IDataSource
{
    override suspend fun getAllNewsIndicator(): IndicatorList
    {
        val response = mWebService.getEconomicIndicatorsInfo()

        return IndicatorList(response.toIndicatorList())
    }
}