package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.local.RateDAO
import com.gnb.gnbtrades.data.remote.Webservice
import com.gnb.gnbtrades.data.local.TransactionDAO
import com.gnb.gnbtrades.data.repository.CommonRepository
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
        webservice: Webservice,
        transactionDAO: TransactionDAO,
        rateDAO: RateDAO
    ) : CommonRepository {
        return CommonRepository(webservice, transactionDAO, rateDAO)
    }
}