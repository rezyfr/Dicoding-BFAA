package com.rezyfr.consumerapp.ui.detail.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.consumerapp.base.BaseViewModel
import com.rezyfr.consumerapp.data.model.UserModel
import com.rezyfr.consumerapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _userFollow = MutableLiveData<List<UserModel>>()
    val userFollow: LiveData<List<UserModel>> = _userFollow

    fun fetchUserFollowers(username: String, key: String) {
        viewModelScope.launch {
            runCatching {
                showLoading()
                if (key == "followers") userRepository.fetchUserFollowers(username)
                else userRepository.fetchUserFollowing(username)
            }.onSuccess {
                _userFollow.value = it
                hideLoading()
            }.onFailure {
                onError(it)
            }
        }
    }
}