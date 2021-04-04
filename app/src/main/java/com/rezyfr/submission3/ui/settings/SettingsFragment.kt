package com.rezyfr.submission3.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.databinding.FragmentSettingsBinding
import com.rezyfr.submission3.service.AlarmHelper
import com.rezyfr.submission3.utils.Constant.ALARM_ID_REPEATING
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_settings

    override val viewModel by viewModels<SettingsViewModel>()
    override var title = MutableLiveData("Reminder Settings")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showToolbarMenu(false)
        binding.swReminder.apply {
            isChecked = viewModel.getReminderSettings()
            setOnCheckedChangeListener { _, isChecked ->
                setReminder(isChecked)
            }
        }
    }

    private fun setReminder(checked: Boolean) {
        viewModel.saveReminderSettings(checked)
        if (checked) {
            AlarmHelper.createAlarm(
                requireContext(),
                getString(R.string.app_name),
                "Let's find more popular user on Github!",
                ALARM_ID_REPEATING,
                Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, 9)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }
            )
        } else {
            AlarmHelper.cancelAlarm(requireContext(), ALARM_ID_REPEATING)
        }
    }

    override fun observeData() {
        // NO OP
    }
}