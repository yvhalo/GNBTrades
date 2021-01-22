package com.gnb.gnbtrades.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction

/**
 * Room AppDatabase
 */
@Database(entities = [Transaction::class, Rate::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDAO(): TransactionDAO
    abstract fun rateDAO(): RateDAO
}