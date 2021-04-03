package com.rezyfr.submission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.data.repository.FavoriteRepository
import com.rezyfr.submission3.data.repository.UserRepository
import com.rezyfr.submission3.data.response.UserDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository
): BaseViewModel() {

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail: LiveData<UserDetailResponse> = _userDetail

    fun fetchUserDetail(username: String){
        viewModelScope.launch {
            runCatching {
                showLoading()
                userRepository.fetchUserDetail(username)
            }.onSuccess {
                _userDetail.value = it
                hideLoading()
            }.onFailure {
                onError(it)
            }
        }
    }

    fun addUserToFavorite(model: UserDetailResponse){
        viewModelScope.launch(Dispatchers.IO){
            favoriteRepository.addFavoriteUser(model.toFavoriteEntity())
        }
    }
}