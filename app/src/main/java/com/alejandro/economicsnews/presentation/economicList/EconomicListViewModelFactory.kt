package com.alejandro.economicsnews.presentation.economicList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alejandro.economicsnews.repository.economicInfo.IEconomicNewsRepository

class EconomicListViewModelFactory(private val mRepository: IEconomicNewsRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return modelClass.getConstructor(IEconomicNewsRepository::class.java).newInstance(mRepository)
    }
}