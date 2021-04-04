package com.rezyfr.consumerapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.rezyfr.consumerapp.R
import com.rezyfr.consumerapp.ui.MainActivity
import com.rezyfr.consumerapp.utils.hideLoadingDialog
import com.rezyfr.consumerapp.utils.showLoadingDialog
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    @LayoutRes
    protected abstract fun layoutRes(): Int
    protected abstract val viewModel: VM

    lateinit var binding: T
    abstract var title: MutableLiveData<String>
    open var toolbarBackButton = MutableLiveData(false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)

        title.observe(viewLifecycleOwner, {
            setToolbar(it)
        })
        showToolbarBackButton()
        observeData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                handleLoading(it)
            }
            errorMessage.observe(viewLifecycleOwner) {
                when (it) {
                    is HttpException -> {
                        handleErrorMessage(it.message())
                    }
                    is SocketTimeoutException -> {
                        handleErrorMessage(getString(R.string.connect_timeout))
                    }
                    is ConnectException, is UnknownHostException -> {
                        handleErrorMessage(getString(R.string.no_internet_connection))
                    }
                    else -> {
                        handleErrorMessage(it.message)
                    }
                }
            }
        }
    }

    abstract fun observeData()

    private fun setToolbar(title: String) = (activity as MainActivity).changeToolbarTitle(title)

    open fun showToolbarBackButton(show: Boolean = true) = (activity as MainActivity).setToolbarBackButton(show)

    open fun handleLoading(isLoading: Boolean) {
        if (isLoading) context?.showLoadingDialog() else hideLoadingDialog()
    }

    fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        hideLoadingDialog()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}