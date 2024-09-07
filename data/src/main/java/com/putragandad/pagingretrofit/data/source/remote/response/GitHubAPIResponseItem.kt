package com.putragandad.pagingretrofit.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.putragandad.pagingretrofit.domain.model.Repo

data class GitHubAPIResponseItem(
    @SerializedName("full_name")
    val fullName: String
)

fun GitHubAPIResponseItem.toDomain() = Repo(
    username = fullName
)