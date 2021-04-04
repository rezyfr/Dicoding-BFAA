package com.rezyfr.submission3.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(),
    FavoriteAdapter.ItemClickListener {

    private var adapter = FavoriteAdapter(this)
    override fun layoutRes() = R.layout.fragment_favorite
    override var title = MutableLiveData("Favorite User")
    override val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        showToolbarMenu()
        showToolbarBackButton()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFavoriteList()
        binding.rvFavorite.adapter = adapter
    }

    override fun observeData() {
        viewModel.favoriteList.observe(viewLifecycleOwner, {
            it?.let {
                adapter.updateData(it)
            }
        })
    }

    override fun onUserClicked(view: View, data: UserFavoriteEntity) {
        val action =
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(data.login, null)
        findNavController().navigate(action)
    }
}