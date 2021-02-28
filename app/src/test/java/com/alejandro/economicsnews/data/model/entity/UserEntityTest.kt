package com.alejandro.economicsnews.data.model.entity

import org.junit.Assert.*
import org.junit.Test

class UserEntityTest
{
    @Test
    fun givenUserDataWhenAllDataIsValidThenCreateUserEntity()
    {
        var flag = true

        try
        {
            UserEntity(1, "Alejandro", "alejandro@mail.com", "123456")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertTrue(flag)
    }

    @Test
    fun givenUserDataWhenIdIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            UserEntity(-1, "Alejandro", "alejandro@mail.com", "123456")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenUserDataWhenNameIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            UserEntity(1, "", "alejandro@mail.com", "123456")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenUserDataWhenUsernameIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            UserEntity(1, "Alejandro", "", "123456")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenUserDataWhenAccessIsInvalidThenReturnException()
    {
        var flag = true

        try
        {
            UserEntity(1, "Alejandro", "alejandro@mail.com", "")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }

    @Test
    fun givenUserDataWhenAllDataAreInvalidThenReturnException()
    {
        var flag = true

        try
        {
            UserEntity(-1, "", "", "")
        }
        catch (e: Exception)
        {
            flag = false
        }

        assertFalse(flag)
    }
}