package com.putragandad.pagingretrofit.di

import com.putragandad.pagingretrofit.data.source.remote.RemoteDataSource
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { RemoteDataSource(get()) }
    }
}