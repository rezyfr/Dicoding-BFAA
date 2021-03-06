package com.rezyfr.submission3.ui

import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseActivity
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.databinding.ActivityMainBinding
import com.rezyfr.submission3.ui.detail.DetailFragmentDirections
import com.rezyfr.submission3.ui.favorite.FavoriteFragmentDirections
import com.rezyfr.submission3.ui.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {

    override fun contentView() = R.layout.activity_main

    override val viewModel by viewModels<MainViewModel>()

    private lateinit var navHostFragment: NavHostFragment
    var isMenuShown = false

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
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
        supportActionBar?.setDisplayShowHomeEnabled(show)
    }

    fun setToolbarMenu(show: Boolean, isHome: Boolean = false) {
        if (show && !isMenuShown) {
            isMenuShown = true
            binding.toolbarMain.inflateMenu(R.menu.menu_home)
            binding.toolbarMain.menu
        }

        val register: MenuItem = binding.toolbarMain.menu.findItem(R.id.menu_favorite)
        register.isVisible = isHome

        binding.toolbarMain.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_favorite -> {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                    navHostFragment.findNavController().navigate(action)
                }
                R.id.menu_settings -> {
                    val action = when (navHostFragment.findNavController().currentDestination?.id) {
                        R.id.homeFragment -> HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
                        R.id.userDetailFragment -> DetailFragmentDirections.actionDetailFragmentToSettingsFragment()
                        R.id.favoriteFragment -> FavoriteFragmentDirections.actionFavoriteFragmentToSettingsFragment()
                        else -> HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
                    }
                    navHostFragment.findNavController().navigate(action)
                }
            }
            false
        }
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}