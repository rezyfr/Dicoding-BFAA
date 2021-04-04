package com.rezyfr.consumerapp.ui

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.rezyfr.consumerapp.R
import com.rezyfr.consumerapp.base.BaseActivity
import com.rezyfr.consumerapp.base.BaseViewModel
import com.rezyfr.consumerapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override fun contentView() = R.layout.activity_main

    override val viewModel by viewModels<MainViewModel>()

    private lateinit var navHostFragment: NavHostFragment

    override fun setupView() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
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

    private fun setupNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}