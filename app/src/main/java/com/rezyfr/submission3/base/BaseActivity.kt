package com.rezyfr.submission3.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rezyfr.submission3.R
import com.rezyfr.submission3.utils.hideLoadingDialog
import com.rezyfr.submission3.utils.showLoadingDialog
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseActivity<VM : BaseViewModel, T : ViewDataBinding> : AppCompatActivity() {

    @LayoutRes
    protected abstract fun contentView(): Int
    protected abstract val viewModel: VM
    protected abstract fun setupView()
    protected lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, contentView())
        setupView()
        observeErrorEvent()
    }

    protected fun observeErrorEvent() {
        viewModel.apply {
            isLoading.observe(this@BaseActivity) {
                handleLoading(it)
            }
            errorMessage.observe(this@BaseActivity) {
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

    open fun handleLoading(isLoading: Boolean) {
        if (isLoading) showLoading() else hideLoading()
    }

    fun showLoading() {
        showLoadingDialog()
    }

    fun hideLoading(){
        hideLoadingDialog()
    }

    fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        hideLoading()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}