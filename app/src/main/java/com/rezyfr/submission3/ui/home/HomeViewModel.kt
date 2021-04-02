package com.rezyfr.submission3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rezyfr.submission3.base.BaseViewModel
import com.rezyfr.submission3.data.repository.UserRepository
import com.rezyfr.submission3.data.response.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel() {

    private val _userList = MutableLiveData<SearchResponse>()
    val userList: LiveData<SearchResponse> = _userList

    fun fetchUserList(query: String){
        viewModelScope.launch {
            showLoading()
            runCatching {
                userRepository.fetchUserList(query)
            }.onSuccess {
                _userList.value = it
            }.onFailure {
                onError(it)
            }
        }
    }

}