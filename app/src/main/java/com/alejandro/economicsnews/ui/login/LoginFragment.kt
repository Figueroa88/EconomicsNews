package com.alejandro.economicsnews.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alejandro.economicsnews.R
import com.alejandro.economicsnews.core.ResourceStatus
import com.alejandro.economicsnews.core.SharedPreferenceHelper
import com.alejandro.economicsnews.data.local.AppDatabase
import com.alejandro.economicsnews.data.local.LocalDataSource
import com.alejandro.economicsnews.databinding.FragmentLoginBinding
import com.alejandro.economicsnews.presentation.login.LoginViewModel
import com.alejandro.economicsnews.presentation.login.LoginViewModelFactory
import com.alejandro.economicsnews.repository.user.UserRepositoryImpl
import com.alejandro.economicsnews.ui.economicList.EconomicListFragmentDirections
import java.lang.Exception


class LoginFragment : Fragment(R.layout.fragment_login)
{
    //Variables

    private lateinit var mViewBinding: FragmentLoginBinding
    private val mViewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory(
            UserRepositoryImpl(
                LocalDataSource(
                    AppDatabase.getInstance(requireContext()).userDao(),
                    AppDatabase.getInstance(requireContext()).indicatorDao()
                )
            )
        )
    }

    //Override

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        //Initº

        initView(view)
        initEvents()
    }

    override fun onStart()
    {
        super.onStart()

        mViewBinding.tilUsername.editText?.setText(
            SharedPreferenceHelper.getLastUsernameSuccess(
                requireContext()
            )
        )
    }

    //Init

    private fun initView(view: View)
    {
        mViewBinding = FragmentLoginBinding.bind(view)
    }

    private fun initEvents()
    {
        onClickTextViewRegisterTest()
        onClickButtonEnterApp()
    }

    //Events

    private fun onClickTextViewRegisterTest()
    {
        mViewBinding.tvRegisterTest.setOnClickListener {

            mViewModel.saveNewUser("Alejandro Figueroa", "alejandro@mail.com", "123456")
                .observe(viewLifecycleOwner, { result ->

                    when (result)
                    {
                        is ResourceStatus.Loading ->
                        {
                            mViewBinding.lpbLoading.visibility = View.VISIBLE
                        }
                        is ResourceStatus.Success ->
                        {
                            mViewBinding.lpbLoading.visibility = View.INVISIBLE

                            Toast.makeText(
                                requireContext(),
                                "Usuario Registrado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is ResourceStatus.Failure ->
                        {
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

    private fun onClickButtonEnterApp()
    {
        mViewBinding.btnLogin.setOnClickListener {

            mViewModel.isHaveUserAccess(getUsername(), getPassword())
                .observe(viewLifecycleOwner, { result ->

                    when (result)
                    {
                        is ResourceStatus.Loading ->
                        {
                            mViewBinding.lpbLoading.visibility = View.VISIBLE
                        }
                        is ResourceStatus.Success ->
                        {
                            mViewBinding.lpbLoading.visibility = View.INVISIBLE

                            if (result.data != null && result.data != "" && result.data != "null")
                            {
                                SharedPreferenceHelper.saveLastUsernameSuccess(
                                    requireContext(),
                                    getUsername()
                                )

                                val actionLogin =
                                    LoginFragmentDirections.actionLoginFragmentToEconomicListFragment(
                                        result.data
                                    )

                                findNavController().navigate(actionLogin)
                            }
                            else
                            {
                                Toast.makeText(
                                    requireContext(),
                                    "Usuario Inválido",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        is ResourceStatus.Failure ->
                        {
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

    //Methods

    private fun getUsername(): String
    {
        return try
        {
            mViewBinding.tilUsername.editText?.text.toString()
        }
        catch (e: Exception)
        {
            ""
        }
    }

    private fun getPassword(): String
    {
        return try
        {
            mViewBinding.tilPassword.editText?.text.toString()
        }
        catch (e: Exception)
        {
            ""
        }
    }
}