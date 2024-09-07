package com.putragandad.pagingretrofit.di

import com.putragandad.pagingretrofit.domain.usecase.SearchRepositoryUseCase
import org.koin.dsl.module

object DomainModule {
    val useCaseModule = module {
        factory { SearchRepositoryUseCase(get()) }
    }
}