package com.rezyfr.consumerapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.rezyfr.consumerapp.R
import com.rezyfr.consumerapp.base.BaseFragment
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import com.rezyfr.consumerapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),
    HomeAdapter.ItemClickListener {

    override fun layoutRes() = R.layout.fragment_home
    override val viewModel by viewModels<HomeViewModel>()
    override var title = MutableLiveData("Github Search User")

    private var adapter = HomeAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showToolbarBackButton(false)
        viewModel.fetchFavoriteList()
        binding.apply {
            rvSearch.adapter = adapter
        }
    }

    override fun onUserClicked(view: View, data: UserDetailResponse) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data.login, null)
        findNavController().navigate(action)
    }

    override fun observeData() {
        viewModel.favoriteList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.updateData(it)
            }
        })
    }
}