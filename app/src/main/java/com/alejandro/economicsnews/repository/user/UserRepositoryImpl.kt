package com.alejandro.economicsnews.repository.user

import com.alejandro.economicsnews.data.local.ILocalDataSource
import com.alejandro.economicsnews.data.model.entity.UserEntity

class UserRepositoryImpl(private val mDataSource: ILocalDataSource) : IUserRepository
{
    override suspend fun saveNewUser(newUser: UserEntity)
    {
        mDataSource.saveUser(newUser)
    }

    override suspend fun isUserHaveAccess(userValidate: UserEntity): String? = mDataSource.isUserHaveAccess(userValidate)
}