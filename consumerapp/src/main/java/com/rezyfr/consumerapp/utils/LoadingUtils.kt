package com.rezyfr.consumerapp.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.*
import com.rezyfr.consumerapp.R


var loadingDialog: Dialog? = null

fun Context?.showLoadingDialog(
    cancelable: Boolean = false,
    canceledOnTouchOutside: Boolean = false
): AlertDialog? {
    val context = this ?: return null
    return AlertDialog.Builder(this).apply {
        setView(R.layout.dialog_loading)
    }.create().apply {
        setCancelable(cancelable)
        setCanceledOnTouchOutside(canceledOnTouchOutside)
        window?.setDimAmount(0.0f)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
        if (context is LifecycleOwner) {
            context.lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                fun onDestroy() {
                    this@apply.dismiss()
                    if (loadingDialog === this@apply) {
                        loadingDialog = null
                    }
                }
            })
        }
        loadingDialog = this
        show()
    }
}

fun hideLoadingDialog() {
    if (loadingDialog?.isShowing == true) {
        loadingDialog?.dismiss()
    }
}