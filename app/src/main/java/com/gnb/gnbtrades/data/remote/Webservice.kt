package com.gnb.gnbtrades.data.remote

import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction
import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit webservice interface
 * Contains application API methods
 */
interface Webservice {

    /**
     * Gets all rates
     */
    @GET("/rates")
    suspend fun getRates() : List<Rate>

    /**
     * Gets all transactions
     */
    @GET("/transactions")
    suspend fun getTransactions() : List<Transaction>
}