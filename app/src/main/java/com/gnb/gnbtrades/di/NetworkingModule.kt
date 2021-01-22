package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.adapters.JSONAdapter
import com.gnb.gnbtrades.data.remote.Webservice
import com.gnb.gnbtrades.data.remote.interceptors.RequestInterceptor
import com.squareup.moshi.Moshi
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
    fun provideRetrofit(okHttpClient : OkHttpClient, moshi : Moshi): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
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

    /**
     * Provides Moshi JSON converter
     * @return Moshi JSON converted instance with custom JSONAdapter
     */
    @Singleton
    @Provides
    fun providesMoshi() : Moshi {
        return Moshi.Builder()
                .add(JSONAdapter())
                .build()
    }
}