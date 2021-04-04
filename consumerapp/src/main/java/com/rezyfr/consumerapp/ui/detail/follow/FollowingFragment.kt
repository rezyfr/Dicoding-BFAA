package com.rezyfr.consumerapp.ui.detail.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.rezyfr.consumerapp.R
import com.rezyfr.consumerapp.base.BaseFragment
import com.rezyfr.consumerapp.data.model.UserModel
import com.rezyfr.consumerapp.databinding.FragmentFollowBinding
import com.rezyfr.consumerapp.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : BaseFragment<FragmentFollowBinding, FollowViewModel>(),
    FollowAdapter.ItemClickListener {

    private var rvUserAdapter = FollowAdapter(this)

    companion object {
        fun newInstance(key: String, username: String): Fragment {
            val bundle = Bundle().apply {
                putString(Constant.ARGS_FRAGMENT_KEY, key)
                putString(Constant.ARGS_USERNAME, username)
            }

            return FollowingFragment().apply {
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

    override fun onUserClicked(view: View, data: UserModel) {
        val action =
            FollowingFragmentDirections.actionFollowingFragmentToDetailFragment(data.login, null)
        findNavController().navigate(action)
    }
}