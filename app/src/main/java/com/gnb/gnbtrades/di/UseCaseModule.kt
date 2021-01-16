package com.gnb.gnbtrades.di

import com.gnb.gnbtrades.data.Webservice
import com.gnb.gnbtrades.domain.repository.CommonRepository
import com.gnb.gnbtrades.domain.usecase.ProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * UseCaseModule
 * Dependency Injection. Provides project useCase instances
 */
@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    /**
     * Provides ProductUseCase instance.
     * @param commonRepository
     * @return ProductUseCase
     */
    @Singleton
    @Provides
    fun providesProductUseCase(
        commonRepository: CommonRepository
    ) : ProductUseCase {
        return ProductUseCase(commonRepository)
    }
}