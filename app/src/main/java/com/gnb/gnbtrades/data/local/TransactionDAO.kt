package com.gnb.gnbtrades.data.local

import androidx.core.content.contentValuesOf
import androidx.lifecycle.LiveData
import androidx.room.*
import com.gnb.gnbtrades.data.entities.Transaction

/**
 * TransactionDAO. Data access object for managing information
 */
@Dao
interface TransactionDAO {

    /**
     * Gets unique products
     */
    @Query("SELECT DISTINCT sku FROM `transaction` ORDER BY sku")
    fun getProducts(): LiveData<List<Transaction>>


    /**
     * Gets transactions from an specific products
     * @param productId selected product
     */
    @Query("SELECT * FROM `transaction` WHERE sku IN (:productId)")
    fun getProductTransactions(productId: String): LiveData<List<Transaction>>

    /**
     * Insert all transactions
     * @param transactions list of transactions
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<Transaction>)

    /**
     * Replace database data
     */
    @androidx.room.Transaction
    suspend fun replaceAll(transactions: List<Transaction>) {
        deleteAll()
        insertAll(transactions)
    }

    /**
     * Delete all from transaction database
     */
    @Query("DELETE FROM `transaction`")
    suspend fun deleteAll()
}