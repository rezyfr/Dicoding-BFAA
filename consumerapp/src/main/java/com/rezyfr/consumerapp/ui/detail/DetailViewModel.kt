package com.rezyfr.consumerapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.consumerapp.base.BaseViewModel
import com.rezyfr.consumerapp.data.repository.FavoriteRepository
import com.rezyfr.consumerapp.data.repository.UserRepository
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    private val _userDetail = MutableLiveData<UserDetailResponse>()
    val userDetail: LiveData<UserDetailResponse> = _userDetail
    private val _favoriteEntity = MutableLiveData<UserDetailResponse?>()
    val favoriteEntity: LiveData<UserDetailResponse?> = _favoriteEntity

    fun fetchUserDetail(username: String) {
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

    fun addUserToFavorite(model: UserDetailResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addFavoriteUser(model)
        }
        _favoriteEntity.value = model
    }

    fun checkIsUserFavorited(userId: Int) {
        viewModelScope.launch {
            val isFavorite = withContext(Dispatchers.IO) {
                favoriteRepository.checkFavoriteUser(userId)
            }
            _favoriteEntity.value = isFavorite.value
        }
    }

    fun deleteUserFromFavorite(model: UserDetailResponse) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFavoriteUser(model)
        }
        _favoriteEntity.value = null
    }
}