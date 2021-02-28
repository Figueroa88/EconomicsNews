package com.alejandro.economicsnews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alejandro.economicsnews.data.model.entity.UserEntity

@Dao
interface IUserDao
{
    @Query("SELECT EXISTS (SELECT 1 FROM userentity WHERE username = :username AND access = :access)")
    fun isUserHaveAccess(username: String, access: String): Boolean

    @Query("SELECT name FROM userentity WHERE username = :username AND access = :access")
    fun isUserHaveAccessName(username: String, access: String): String?

    @Query("SELECT EXISTS (SELECT 1 FROM userentity WHERE username = :username)")
    fun isUserExist(username: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("DELETE FROM userentity")
    fun deleteAll()

    @Query("SELECT * FROM userentity WHERE id = :id")
    fun get(id: Long): UserEntity
}