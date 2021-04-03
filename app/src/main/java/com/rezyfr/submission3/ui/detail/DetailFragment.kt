package com.rezyfr.submission3.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun layoutRes() = R.layout.fragment_detail
    override var title = MutableLiveData("Detail User")
    override val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fabDetail.show()
            arguments?.let {
                val username = DetailFragmentArgs.fromBundle(it).username
                viewModel.fetchUserDetail(username)

                vpProfile.adapter =
                    DetailPagerAdapter(
                        childFragmentManager,
                        username
                    )
                tablayoutProfile.setupWithViewPager(vpProfile)
            }

            fabDetail.setOnClickListener {
                user?.let { user ->
                    viewModel.addUserToFavorite(user)
                    fabDetail.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_filled))
                }
            }
        }
    }

    override fun observeData() {
        viewModel.userDetail.observe(viewLifecycleOwner, {
            binding.user = it
            binding.fabDetail.show()
        })
    }
}