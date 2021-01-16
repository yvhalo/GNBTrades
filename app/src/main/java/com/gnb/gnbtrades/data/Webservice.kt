package com.gnb.gnbtrades.data

import com.gnb.gnbtrades.domain.entities.Rate
import com.gnb.gnbtrades.domain.entities.Transaction
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Retrofit webservice interface
 * Contains application API methods
 */
interface Webservice {

    /**
     * Gets all rates
     */
    @GET("/rates")
    fun getRates() : Call<List<Rate>>

    /**
     * Gets all transactions
     */
    @GET("/transactions")
    fun getTransactions() : Call<List<Transaction>>
}