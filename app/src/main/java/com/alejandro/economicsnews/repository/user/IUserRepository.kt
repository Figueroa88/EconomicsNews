package com.alejandro.economicsnews.repository.user

import com.alejandro.economicsnews.data.model.entity.UserEntity

interface IUserRepository
{
    suspend fun saveNewUser(newUser: UserEntity)

    suspend fun isUserHaveAccess(userValidate:UserEntity): String?
}