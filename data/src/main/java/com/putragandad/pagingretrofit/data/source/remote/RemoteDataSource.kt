package com.putragandad.pagingretrofit.data.source.remote

import com.putragandad.pagingretrofit.data.source.remote.response.GitHubAPIResponseItem

class RemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun fetchRepos(username: String, page: Int, perPage: Int) : List<GitHubAPIResponseItem> {
        return apiService.fetchRepos(username, page, perPage)
    }
}