package com.putragandad.pagingretrofit.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.putragandad.pagingretrofit.data.source.remote.RemoteDataSource
import com.putragandad.pagingretrofit.data.source.remote.response.toDomain
import com.putragandad.pagingretrofit.domain.model.Repo

private const val INITIAL_PAGE = 1

class GithubRepoPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val username: String
) : PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = remoteDataSource.fetchRepos(username, page, params.loadSize)
            LoadResult.Page(
                data = response.map { it.toDomain() },
                prevKey = if(page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (t: Throwable) {
            LoadResult.Error(t)
        }
    }

}