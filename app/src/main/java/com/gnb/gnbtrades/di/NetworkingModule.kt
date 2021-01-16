package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.Webservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
     * Provides Retrofit instance
     * @return retrofit Builder to be used when providing APiService
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://quiet-stone-2094.herokuapp.com")

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