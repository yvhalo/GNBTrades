package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.remote.Webservice
import com.gnb.gnbtrades.data.remote.interceptors.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * NetworkingModule
 * Dependency Injection. Provides instances of networking related classes
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkingModule {


    /**
     * Provides OkHttpClient instance
     * @return custom OkHttp client
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(RequestInterceptor())
            .build()

    }

    /**
     * Provides Retrofit instance
     * @param okHttpClient custom rest client
     * @return retrofit Builder to be used when providing APiService
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient : OkHttpClient): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://quiet-stone-2094.herokuapp.com")
            .client(okHttpClient)

    }

    /**
     * Provides ApiService instance
     * @return Retrofit webservice
     */
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): Webservice {
        return retrofit
            .build()
            .create(Webservice::class.java)
    }
}