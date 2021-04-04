package com.rezyfr.consumerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.consumerapp.base.BaseViewModel
import com.rezyfr.consumerapp.data.repository.FavoriteRepository
import com.rezyfr.consumerapp.data.response.UserDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    private val _favoriteList = MutableLiveData<List<UserDetailResponse>?>()
    val favoriteList: LiveData<List<UserDetailResponse>?> = _favoriteList

    fun fetchFavoriteList() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) { favoriteRepository.getFavoriteUsers() }
            _favoriteList.value = data.value
        }
    }
}