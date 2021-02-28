package com.alejandro.economicsnews.core

import android.content.Context

object SharedPreferenceHelper
{
    private const val PREFERENCE_FILES = "EconomicPreferences"

    private const val KEY_USERNAME = "KeyUsernameApp"
    private const val KEY_LOGOUT = "KeyLogoutApp"

    fun saveIsPressLogoutApp(context: Context, value: Boolean)
    {
        val preference = context.getSharedPreferences(PREFERENCE_FILES, Context.MODE_PRIVATE)

        preference.edit().putBoolean(KEY_LOGOUT, value).apply()
    }

    fun isUserPressLogoutApp(context: Context): Boolean
    {
        val preference = context.getSharedPreferences(PREFERENCE_FILES, Context.MODE_PRIVATE)

        return preference.getBoolean(KEY_LOGOUT, false)
    }

    fun saveLastUsernameSuccess(context: Context, value: String)
    {
        val preference = context.getSharedPreferences(PREFERENCE_FILES, Context.MODE_PRIVATE)

        preference.edit().putString(KEY_USERNAME, value).apply()
    }

    fun getLastUsernameSuccess(context: Context): String
    {
        val preference = context.getSharedPreferences(PREFERENCE_FILES, Context.MODE_PRIVATE)

        return preference.getString(KEY_USERNAME, "")!!
    }
}