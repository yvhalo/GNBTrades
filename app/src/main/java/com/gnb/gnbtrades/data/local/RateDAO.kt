package com.gnb.gnbtrades.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction

/**
 * RateDAO. Data access object for managing rates information
 */
@Dao
interface RateDAO {

    /**
     * Gets rates
     */
    @Query("SELECT * FROM `rates`")
    suspend fun getRates(): List<Rate>

    /**
     * Insert all rates
     * @param rates list of rates
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<Rate>)

    /**
     * Replace database data
     */
    @androidx.room.Transaction
    suspend fun replaceAll(rates: List<Rate>) {
        deleteAll()
        insertAll(rates)
    }

    /**
     * Delete all from rates database
     */
    @Query("DELETE FROM `rates`")
    suspend fun deleteAll()
}