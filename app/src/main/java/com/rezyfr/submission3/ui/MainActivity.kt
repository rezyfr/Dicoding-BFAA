package com.rezyfr.submission3.ui

import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseActivity
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    override fun contentView() = R.layout.activity_main

    override val viewModel by viewModels<MainViewModel>()

    override fun setupView() {
        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        binding.apply {
            setSupportActionBar(toolbarMain)
            tvToolbar.text = getString(R.string.app_name)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun changeToolbarTitle(title: String) {
        binding.tvToolbar.text = title
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}