package com.alejandro.economicsnews.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alejandro.economicsnews.repository.user.IUserRepository

class LoginViewModelFactory(private val mRepository: IUserRepository) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return modelClass.getConstructor(IUserRepository::class.java).newInstance(mRepository)
    }
}