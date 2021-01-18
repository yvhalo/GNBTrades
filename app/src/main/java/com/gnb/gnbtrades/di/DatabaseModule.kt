package com.gnb.gnbtrades.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gnb.gnbtrades.data.local.AppDatabase
import com.gnb.gnbtrades.data.local.TransactionDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * DatabaseModule
 * Dependency Injection. Provides instances of database in application context
 */
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    /**
     * Provides Room database instance
     * @return Room database instance
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase = Room
        .databaseBuilder(appContext, AppDatabase::class.java, "GNBTrades")
        .build()

    /**
     * Provides TransactionDAO instance
     * return TransactionDAO instance
     */
    @Provides
    fun provideTransactionDAO(appDatabase: AppDatabase) = appDatabase.transactionDAO()

    /**
     * Provides RateDAO instance
     * return RateDAO instance
     */
    @Provides
    fun provideRateDAO(appDatabase: AppDatabase) = appDatabase.rateDAO()

}
