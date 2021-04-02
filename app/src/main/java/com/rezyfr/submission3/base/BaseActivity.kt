package com.rezyfr.submission3.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rezyfr.submission3.R
import com.rezyfr.submission3.utils.hideLoadingDialog
import com.rezyfr.submission3.utils.showLoadingDialog
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeErrorEvent()
    }

    protected fun observeErrorEvent() {
        viewModel.apply {
            isLoading.observe(this@BaseActivity) {
                handleLoading(it == true)
            }
            errorMessage.observe(this@BaseActivity) {
                when (it) {
                    is HttpException -> {
                        handleErrorMessage(it.message())
                    }
                    is SocketTimeoutException -> {
                        handleErrorMessage(getString(R.string.connect_timeout))
                    }
                    is ConnectException -> {
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
        hideLoadingDialog()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}