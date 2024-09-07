package com.putragandad.pagingretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.domain.usecase.SearchRepositoryUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchRepositoryUseCase: SearchRepositoryUseCase
) : ViewModel() {
    private val _searchResultState : MutableStateFlow<PagingData<Repo>> = MutableStateFlow(value = PagingData.empty())
    val searchResultState = _searchResultState.asStateFlow()

    fun searchRepos(username: String) = viewModelScope.launch {
        searchRepositoryUseCase.invoke(username)
            .cachedIn(viewModelScope)
            .collect {
                _searchResultState.value = it
            }
    }
}