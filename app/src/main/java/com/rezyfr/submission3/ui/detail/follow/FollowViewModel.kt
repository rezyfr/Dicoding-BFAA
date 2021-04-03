package com.rezyfr.submission3.ui.detail.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.data.model.UserModel
import com.rezyfr.submission3.data.repository.UserRepository
import com.rezyfr.submission3.data.response.UserDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowViewModel @Inject constructor(
    private val userRepository: UserRepository
):BaseViewModel() {
    private val _userFollow = MutableLiveData<List<UserModel>>()
    val userFollow: LiveData<List<UserModel>> = _userFollow

    fun fetchUserFollowers(username: String, key: String){
        viewModelScope.launch {
            runCatching {
                showLoading()
                if(key == "followers") userRepository.fetchUserFollowers(username)
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