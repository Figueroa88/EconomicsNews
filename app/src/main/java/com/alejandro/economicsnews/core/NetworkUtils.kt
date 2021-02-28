package com.alejandro.economicsnews.core

import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

object NetworkUtils
{
    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try
        {
            //Es como hacer un ping
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 2000)
            sock.close()
            true
        } catch (e: Exception)
        {
            false
        }
    }
}