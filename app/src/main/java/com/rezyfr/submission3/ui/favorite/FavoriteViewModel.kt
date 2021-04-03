package com.rezyfr.submission3.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.data.entity.UserFavoriteEntity
import com.rezyfr.submission3.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    private val _favoriteList = MutableLiveData<List<UserFavoriteEntity>?>()
    val favoriteList: LiveData<List<UserFavoriteEntity>?> = _favoriteList

    fun fetchFavoriteList() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) { favoriteRepository.getFavoriteUsers().value }
            _favoriteList.value = data
        }
    }
}