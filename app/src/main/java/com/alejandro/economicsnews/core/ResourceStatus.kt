package com.alejandro.economicsnews.core

import java.lang.Exception

sealed class ResourceStatus<out T>
{
    class Loading<out T> : ResourceStatus<T>()
    data class Success<out T>(val data: T) : ResourceStatus<T>()
    data class Failure(val exception: Exception) : ResourceStatus<Nothing>()
}