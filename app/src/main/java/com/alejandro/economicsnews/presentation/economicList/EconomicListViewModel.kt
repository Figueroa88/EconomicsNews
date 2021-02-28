package com.alejandro.economicsnews.presentation.economicList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.alejandro.economicsnews.core.ResourceStatus
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.repository.economicInfo.IEconomicNewsRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.util.*


class EconomicListViewModel(private val mRepository: IEconomicNewsRepository) : ViewModel()
{
    private var mIndicatorList: MutableList<IndicatorEntity> = mutableListOf()

    fun searchIndicatorList() = liveData(Dispatchers.IO) {

        emit(ResourceStatus.Loading())

        if (mIndicatorList.size != 0)
        {
            emit(ResourceStatus.Success(mIndicatorList))
        }
        else
        {
            try
            {
                val list = mRepository.getIndicatorList()
                mIndicatorList = list.indicatorList

                emit(ResourceStatus.Success(mIndicatorList))
            }
            catch (error: Exception)
            {
                emit(ResourceStatus.Failure(error))
            }
        }
    }

    fun filterIndicatorList(textFilter: String) = liveData(Dispatchers.IO) {

        emit(ResourceStatus.Loading())

        try
        {
            emit(ResourceStatus.Success(getFilterIndicatorList(textFilter)))
        }
        catch (e: Exception)
        {
            emit(ResourceStatus.Failure(e))
        }
    }

    private fun getFilterIndicatorList(textFilter: String): List<IndicatorEntity>
    {
        if (textFilter == "")
        {
            return mIndicatorList
        }
        else
        {
            val listReturn = mutableListOf<IndicatorEntity>()

            mIndicatorList.forEach { indicatorEntity ->
                if (indicatorEntity.code.toLowerCase(Locale.ROOT).contains(textFilter.toLowerCase(Locale.ROOT)))
                {
                    listReturn.add(indicatorEntity)
                }
            }

            return listReturn
        }
    }
}