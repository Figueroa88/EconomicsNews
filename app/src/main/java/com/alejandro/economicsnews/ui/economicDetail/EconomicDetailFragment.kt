package com.alejandro.economicsnews.ui.economicDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.alejandro.economicsnews.R
import com.alejandro.economicsnews.core.toNumberStringFormat
import com.alejandro.economicsnews.databinding.FragmentEconomicDetailBinding


class EconomicDetailFragment : Fragment(R.layout.fragment_economic_detail)
{
    //Variables

    private lateinit var mViewBinding: FragmentEconomicDetailBinding
    private val mViewArgs by navArgs<EconomicDetailFragmentArgs>()

    //Override

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Init

        initView(view)
    }

    //Init

    private fun initView(view: View)
    {
        mViewBinding = FragmentEconomicDetailBinding.bind(view)

        chargeIndicatorData()
    }

    //Methods

    private fun chargeIndicatorData()
    {
        mViewBinding.tvIndicatorName.text = mViewArgs.indicatorName
        mViewBinding.tvIndicatorCode.text = mViewArgs.indicatorCode
        mViewBinding.tvIndicatorUnit.text = mViewArgs.indicatorUnit
        mViewBinding.tvIndicatorDate.text = mViewArgs.indicatorName
        mViewBinding.tvIndicatorValor.text = "$ ${mViewArgs.indicatorValue.toNumberStringFormat()}"
    }
}