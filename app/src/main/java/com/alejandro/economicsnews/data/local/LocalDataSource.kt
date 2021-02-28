package com.alejandro.economicsnews.data.local

import com.alejandro.economicsnews.core.SecurityUtils
import com.alejandro.economicsnews.data.local.dao.IIndicatorDao
import com.alejandro.economicsnews.data.local.dao.IUserDao
import com.alejandro.economicsnews.data.model.IndicatorList
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.data.model.entity.UserEntity

class LocalDataSource(private val mUserDao: IUserDao, private val mIndicatorDao: IIndicatorDao) :
    ILocalDataSource
{
    override suspend fun getAllNewsIndicator(): IndicatorList =
        IndicatorList(mIndicatorDao.getAll())

    override suspend fun saveUser(newUser: UserEntity)
    {
        mUserDao.deleteAll()

        mUserDao.insertUser(newUser)
    }

    override suspend fun isUserHaveAccess(userValidate: UserEntity): String?
    {
        var nameReturn = ""

        val user = mUserDao.get(1)

        val username = SecurityUtils.getAESDecrypt(user.username)
        val password = SecurityUtils.getAESDecrypt(user.access)

        if (username == userValidate.username && password == userValidate.access)
        {
            nameReturn = user.name
        }

        return nameReturn
        //return mUserDao.isUserHaveAccessName(userValidate.username, userValidate.access)
    }

    override suspend fun saveIndicator(newIndicator: IndicatorEntity)
    {
        mIndicatorDao.insertUser(newIndicator)
    }
}