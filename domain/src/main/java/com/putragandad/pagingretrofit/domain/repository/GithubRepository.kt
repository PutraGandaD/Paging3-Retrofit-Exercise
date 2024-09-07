package com.putragandad.pagingretrofit.domain.repository

import androidx.paging.PagingData
import com.putragandad.pagingretrofit.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun searchRepo(username: String) : Flow<PagingData<Repo>>
}