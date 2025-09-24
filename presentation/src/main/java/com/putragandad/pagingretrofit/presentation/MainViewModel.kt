package com.putragandad.pagingretrofit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.domain.usecase.SearchRepositoryUseCase
import com.putragandad.pagingretrofit.presentation.MainUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val searchRepositoryUseCase: SearchRepositoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainUiState())
    val state = _state.asStateFlow()

    val repos: Flow<PagingData<Repo>> =
        state
            .map { it.searchQuery } // take only the search query from ui state
            .distinctUntilChanged() // prevent re-emit state when the search query isn't distinct
            .debounce(500) // wait 500ms before emitting state
            .filter { it.length >= 2 } // only take if the string length is 2
            .flatMapLatest { query ->
                searchRepositoryUseCase(query) // query the search
            }
            .cachedIn(viewModelScope)

    fun changeSearchQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }
}
