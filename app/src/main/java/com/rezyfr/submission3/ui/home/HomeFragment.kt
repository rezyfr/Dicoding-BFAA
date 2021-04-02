package com.rezyfr.submission3.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.rezyfr.submission3.R
import com.rezyfr.submission3.base.BaseFragment
import com.rezyfr.submission3.data.model.UserModel
import com.rezyfr.submission3.databinding.FragmentHomeBinding
import com.rezyfr.submission3.utils.hideLoadingDialog
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
        binding.apply {
            rvSearch.adapter = adapter
            etSearch.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    var handled = false
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        viewModel.fetchUserList(etSearch.text.toString())
                        handled = true
                    }
                    handled
                }
            }
        }
    }

    override fun onUserClicked(view: View, data: UserModel) {

    }

    override fun observeData() {
        viewModel.userList.observe(viewLifecycleOwner, {
            binding.containerCount.visibility = View.VISIBLE
            adapter.updateData(it.items)
            binding.search = it
            hideLoadingDialog()
        })
    }
}