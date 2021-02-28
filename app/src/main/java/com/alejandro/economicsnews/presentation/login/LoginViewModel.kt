package com.alejandro.economicsnews.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.alejandro.economicsnews.core.ResourceStatus
import com.alejandro.economicsnews.core.SecurityUtils
import com.alejandro.economicsnews.data.model.entity.UserEntity
import com.alejandro.economicsnews.repository.user.IUserRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(private val mRepository: IUserRepository) : ViewModel()
{
    fun saveNewUser(name: String, username: String, password: String) = liveData(Dispatchers.IO) {

        emit(ResourceStatus.Loading())

        try
        {
            val aesUsername = SecurityUtils.getAES(username)
            val aesPassword = SecurityUtils.getAES(password)

            val newUser = UserEntity(1, name, aesUsername, aesPassword)

            emit(ResourceStatus.Success(mRepository.saveNewUser(newUser)))
        }
        catch (error: Exception)
        {
            emit(ResourceStatus.Failure(error))
        }
    }

    fun isHaveUserAccess(username: String, password: String) = liveData(Dispatchers.IO) {

        emit(ResourceStatus.Loading())

        try
        {
            val userValidate = UserEntity(1, username, username, password)

            emit(ResourceStatus.Success(mRepository.isUserHaveAccess(userValidate)))
        }
        catch (error: Exception)
        {
            emit(ResourceStatus.Failure(error))
        }
    }
}