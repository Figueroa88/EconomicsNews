package com.alejandro.economicsnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejandro.economicsnews.R
import com.alejandro.economicsnews.core.SharedPreferenceHelper
import com.alejandro.economicsnews.ui.economicDetail.EconomicDetailFragment
import com.alejandro.economicsnews.ui.login.LoginFragment

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed()
    {
        val navCurrentFragment = supportFragmentManager.findFragmentById(R.id.fcvContent)

        if (navCurrentFragment != null)
        {
            val currentFragmentList = navCurrentFragment.childFragmentManager.fragments

            var isExecuteBack = false

            if (currentFragmentList.size > 0 && currentFragmentList[0] is EconomicDetailFragment)
            {
                isExecuteBack = true
            }
            else if (SharedPreferenceHelper.isUserPressLogoutApp(this))
            {
                SharedPreferenceHelper.saveIsPressLogoutApp(this, false)

                isExecuteBack = true
            }
            else if (currentFragmentList.size > 0 && currentFragmentList[0] is LoginFragment)
            {
                finish()
            }

            if (isExecuteBack)
            {
                super.onBackPressed()
            }
        }
    }
}