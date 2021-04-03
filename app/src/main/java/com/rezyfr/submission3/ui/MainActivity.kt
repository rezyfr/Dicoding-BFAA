package com.rezyfr.submission3.ui

import android.view.View
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

    var isMenuShown = false

    override fun setupView() {
        setupToolbar()
        setupNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    fun changeToolbarTitle(title: String) {
        if (title.isNotEmpty()) {
            binding.toolbarMain.visibility = View.VISIBLE
            binding.toolbarMain.title = title
        }
    }

    fun setToolbarBackButton(show: Boolean) {
        if (show) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }

    fun setToolbarMenu(show: Boolean) {
        if (show && !isMenuShown) {
            isMenuShown = true
            binding.toolbarMain.inflateMenu(R.menu.menu_home)
        }
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}