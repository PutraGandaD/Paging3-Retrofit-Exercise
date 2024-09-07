package com.putragandad.pagingretrofit.di

import com.putragandad.pagingretrofit.data.implementation.GithubRepositoryImpl
import com.putragandad.pagingretrofit.domain.repository.GithubRepository
import org.koin.dsl.module

object DataModule {
    val repositoryModule = module {
        factory <GithubRepository> { GithubRepositoryImpl(get()) }
    }
}