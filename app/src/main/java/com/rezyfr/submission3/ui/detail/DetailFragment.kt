package com.rezyfr.submission3.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    private var favoriteEntity: UserFavoriteEntity? = null
    override fun layoutRes() = R.layout.fragment_detail
    override var title = MutableLiveData("Detail User")
    override val viewModel by viewModels<DetailViewModel>()

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
        binding.apply {
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
                user?.let {
                    if (favoriteEntity != null)
                        viewModel.deleteUserFromFavorite(it)
                    else
                        viewModel.addUserToFavorite(it)
                }
            }
        }
    }

    override fun observeData() {
        viewModel.userDetail.observe(viewLifecycleOwner, {
            binding.user = it
            binding.fabDetail.show()
            viewModel.checkIsUserFavorited(it.id)
        })

        viewModel.favoriteEntity.observe(viewLifecycleOwner, ::observeUserEntity)
    }

    private fun observeUserEntity(userFavoriteEntity: UserFavoriteEntity?){
        if (userFavoriteEntity == null) {
            binding.fabDetail.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_outlined
                )
            )
        } else {
            binding.fabDetail.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_filled
                )
            )
            favoriteEntity = userFavoriteEntity
        }
    }
}