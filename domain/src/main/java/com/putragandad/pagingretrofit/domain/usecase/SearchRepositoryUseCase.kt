package com.putragandad.pagingretrofit.domain.usecase

import androidx.paging.PagingData
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryUseCase(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(username: String) : Flow<PagingData<Repo>> {
        return repository.searchRepo(username)
    }
}