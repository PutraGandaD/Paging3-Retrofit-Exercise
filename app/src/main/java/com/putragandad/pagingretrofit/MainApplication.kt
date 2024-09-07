package com.putragandad.pagingretrofit

import android.app.Application
import com.putragandad.pagingretrofit.di.AppModule.appModule
import com.putragandad.pagingretrofit.di.DataModule.repositoryModule
import com.putragandad.pagingretrofit.di.DomainModule.useCaseModule
import com.putragandad.pagingretrofit.di.NetworkModule.networkModule
import com.putragandad.pagingretrofit.di.PresentationModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                appModule,
                repositoryModule,
                useCaseModule,
                networkModule,
                viewModelModule
            )
        }
    }
}