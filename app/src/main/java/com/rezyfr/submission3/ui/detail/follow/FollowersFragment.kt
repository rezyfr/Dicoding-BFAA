package com.rezyfr.submission3.ui.detail.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.data.model.UserModel
import com.rezyfr.submission3.databinding.FragmentFollowBinding
import com.rezyfr.submission3.ui.home.HomeAdapter
import com.rezyfr.submission3.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : BaseFragment<FragmentFollowBinding, FollowViewModel>(),
    HomeAdapter.ItemClickListener {

    private var rvUserAdapter = HomeAdapter(this)

    companion object {
        fun newInstance(key: String, username: String): Fragment {
            val bundle = Bundle().apply {
                putString(Constant.ARGS_FRAGMENT_KEY, key)
                putString(Constant.ARGS_USERNAME, username)
            }

            return FollowersFragment().apply {
                arguments = bundle
            }
        }
    }

    override val viewModel by viewModels<FollowViewModel>()
    override var title = MutableLiveData("")
    override fun layoutRes() = R.layout.fragment_follow

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvFollow.adapter = rvUserAdapter
        }

        arguments?.let {
            val username = it.getString(Constant.ARGS_USERNAME, "")
            val key = it.getString(Constant.ARGS_FRAGMENT_KEY, "")
            viewModel.fetchUserFollowers(username, key)
        }
    }

    override fun observeData() {
        viewModel.userFollow.observe(viewLifecycleOwner, {
            rvUserAdapter.updateData(it)
        })
    }

//    override fun onUserClickListener(view: View, data: UserSearch) {
//        val action = ProfileFragmentDirections.actionUserDetailFragmentSelf(data.login, null)
//        view.changeNavigation(action)
//    }

    override fun onUserClicked(view: View, data: UserModel) {

    }
}