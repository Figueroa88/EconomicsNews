package com.alejandro.economicsnews.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.fragment.findNavController
import com.alejandro.economicsnews.R
import com.alejandro.economicsnews.databinding.FragmentSplashBinding


class SplashFragment : Fragment(R.layout.fragment_splash)
{
    //Variables

    private lateinit var mViewBinding: FragmentSplashBinding

    //Override

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Init

        initView(view)
        initEvents()
    }

    //Init

    private fun initView(view: View)
    {
        mViewBinding = FragmentSplashBinding.bind(view)
    }

    private fun initEvents()
    {
        eventMotionTransitionAnimation()
    }

    //Events

    private fun eventMotionTransitionAnimation()
    {
        mViewBinding.mlSplashContent.setTransitionListener(object : MotionLayout.TransitionListener
        {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int)
            {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float)
            {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int)
            {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float)
            {
            }
        })
    }
}