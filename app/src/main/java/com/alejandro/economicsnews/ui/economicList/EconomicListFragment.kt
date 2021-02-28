package com.alejandro.economicsnews.ui.economicList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.alejandro.economicsnews.R
import com.alejandro.economicsnews.core.ResourceStatus
import com.alejandro.economicsnews.core.SharedPreferenceHelper
import com.alejandro.economicsnews.data.local.AppDatabase
import com.alejandro.economicsnews.data.local.LocalDataSource
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.data.remote.RemoteDataSource
import com.alejandro.economicsnews.databinding.FragmentEconomicListBinding
import com.alejandro.economicsnews.presentation.economicList.EconomicListViewModel
import com.alejandro.economicsnews.presentation.economicList.EconomicListViewModelFactory
import com.alejandro.economicsnews.repository.client.RetrofitClient
import com.alejandro.economicsnews.repository.economicInfo.EconomicNewsRepositoryImpl


class EconomicListFragment : Fragment(R.layout.fragment_economic_list),
    IndicatorAdapter.OnIndicatorClickListener
{
    //Variables

    private var mIsSearchActive = false
    private lateinit var mViewBinding: FragmentEconomicListBinding
    private val mViewArgs by navArgs<EconomicListFragmentArgs>()
    private val mViewModel by viewModels<EconomicListViewModel> {
        EconomicListViewModelFactory(
            EconomicNewsRepositoryImpl(
                LocalDataSource(
                    AppDatabase.getInstance(requireContext()).userDao(),
                    AppDatabase.getInstance(requireContext()).indicatorDao()
                ),
                RemoteDataSource(RetrofitClient.webService)
            )
        )
    }

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
        mViewBinding = FragmentEconomicListBinding.bind(view)

        mViewBinding.tvNameOfUser.text = "Hola ${mViewArgs.userName}"

        executeSearchIndicators()
    }

    private fun initEvents()
    {
        onClickButtonLogout()
        onDoRefreshRecyclerView()
        onTextChangeEditTextSearchByCode()
    }

    //Events

    private fun onDoRefreshRecyclerView()
    {
        mViewBinding.srlIndicatorListRefresh.setOnRefreshListener {

            mViewBinding.srlIndicatorListRefresh.isRefreshing = false

            if (!mIsSearchActive)
            {
                mIsSearchActive = true

                executeSearchIndicators()
            }
        }
    }

    private fun onClickButtonLogout()
    {
        mViewBinding.btnLogout.setOnClickListener {

            SharedPreferenceHelper.saveIsPressLogoutApp(requireContext(), true)
            requireActivity().onBackPressed()
        }
    }

    private fun onTextChangeEditTextSearchByCode()
    {
        mViewBinding.etSearchIndicator.addTextChangedListener(object : TextWatcher
        {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
            {
            }

            override fun afterTextChanged(textEditable: Editable?)
            {
                textEditable?.let {

                    mViewModel.filterIndicatorList(it.toString())
                        .observe(viewLifecycleOwner, { result ->

                            when (result)
                            {
                                is ResourceStatus.Loading ->
                                {
                                    mViewBinding.lpbLoading.visibility = View.VISIBLE
                                }
                                is ResourceStatus.Success ->
                                {
                                    mIsSearchActive = false

                                    mViewBinding.lpbLoading.visibility = View.INVISIBLE

                                    mViewBinding.rvIndicatorList.adapter =
                                        IndicatorAdapter(result.data, this@EconomicListFragment)
                                }
                                is ResourceStatus.Failure ->
                                {
                                    mIsSearchActive = false

                                    mViewBinding.lpbLoading.visibility = View.INVISIBLE

                                    Toast.makeText(
                                        requireContext(),
                                        result.exception.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        })
                }
            }
        })
    }

    override fun onIndicatorClick(indicator: IndicatorEntity)
    {
        val actionIndicatorDetail =
            EconomicListFragmentDirections.actionEconomicListFragmentToEconomicDetailFragment(
                indicator.code,
                indicator.name,
                indicator.unit,
                indicator.date,
                indicator.value.toFloat()
            )

        findNavController().navigate(actionIndicatorDetail)
    }

    //Methods

    private fun executeSearchIndicators()
    {
        mViewModel.searchIndicatorList().observe(viewLifecycleOwner, { result ->

            when (result)
            {
                is ResourceStatus.Loading ->
                {
                    mViewBinding.lpbLoading.visibility = View.VISIBLE
                }
                is ResourceStatus.Success ->
                {
                    mIsSearchActive = false

                    mViewBinding.lpbLoading.visibility = View.INVISIBLE

                    mViewBinding.rvIndicatorList.adapter = IndicatorAdapter(result.data, this)
                }
                is ResourceStatus.Failure ->
                {
                    mIsSearchActive = false

                    mViewBinding.lpbLoading.visibility = View.INVISIBLE

                    Toast.makeText(requireContext(), result.exception.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}