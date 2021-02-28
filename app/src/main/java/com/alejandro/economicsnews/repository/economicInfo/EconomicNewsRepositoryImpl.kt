package com.alejandro.economicsnews.repository.economicInfo

import com.alejandro.economicsnews.core.NetworkUtils
import com.alejandro.economicsnews.data.IDataSource
import com.alejandro.economicsnews.data.local.ILocalDataSource
import com.alejandro.economicsnews.data.model.IndicatorList

class EconomicNewsRepositoryImpl(
    private val mLocalDataSource: ILocalDataSource,
    private val mRemoteDataSource: IDataSource
) : IEconomicNewsRepository
{
    override suspend fun getIndicatorList(): IndicatorList
    {
        if (NetworkUtils.isNetworkAvailable())
        {
            mRemoteDataSource.getAllNewsIndicator().indicatorList.forEach { indicatorEntity ->
                mLocalDataSource.saveIndicator(indicatorEntity)
            }
        }

        return mLocalDataSource.getAllNewsIndicator()
    }
}