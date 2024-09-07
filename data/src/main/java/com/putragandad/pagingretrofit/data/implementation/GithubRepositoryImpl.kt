package com.putragandad.pagingretrofit.data.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.putragandad.pagingretrofit.data.source.remote.RemoteDataSource
import com.putragandad.pagingretrofit.data.source.remote.paging.GithubRepoPagingSource
import com.putragandad.pagingretrofit.domain.model.Repo
import com.putragandad.pagingretrofit.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : GithubRepository {
    override suspend fun searchRepo(username: String): Flow<PagingData<Repo>> = Pager(
        pagingSourceFactory = { GithubRepoPagingSource(remoteDataSource, username) },
        config = PagingConfig(
            pageSize = 50
        )
    ).flow

}