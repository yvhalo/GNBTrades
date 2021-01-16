package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.Webservice
import com.gnb.gnbtrades.domain.repository.CommonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * RepositoryModule
 * Dependency Injection. Provides project repository instances
 */
@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    /**
     * Provides commonRepository instance
     * @param  webservice Retrofit interface
     * @return CommonRepository
     */
    @Singleton
    @Provides
    fun provideCommonRepository(
        webservice: Webservice
    ) : CommonRepository {
        return CommonRepository(webservice)
    }
}