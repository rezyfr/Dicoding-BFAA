package com.rezyfr.submission3.ui.settings

import android.content.SharedPreferences
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.utils.Constant.PREF_REMINDER
import com.rezyfr.submission3.utils.SharedPrefUtils.saveBoolean
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    fun saveReminderSettings(isOn: Boolean) {
        sharedPreferences.saveBoolean(PREF_REMINDER, isOn)
    }

    fun getReminderSettings(): Boolean = sharedPreferences.getBoolean(PREF_REMINDER, false)

}