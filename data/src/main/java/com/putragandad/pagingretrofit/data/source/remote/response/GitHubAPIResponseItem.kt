package com.putragandad.pagingretrofit.data.source.remote.response

import com.putragandad.pagingretrofit.domain.model.Repo

data class GitHubAPIResponseItem(
    val fullName: String
)

fun GitHubAPIResponseItem.toDomain() = Repo(
    username = fullName
)