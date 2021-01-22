package com.gnb.gnbtrades.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.remote.Webservice
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.data.local.RateDAO
import com.gnb.gnbtrades.data.local.TransactionDAO
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * CommonRepository
 * Fetch data from application dataSources.
 */

// TODO manage result options (Success, Error)
class CommonRepository @Inject constructor(private val webservice: Webservice,
                                           private val transactionDAO: TransactionDAO,
                                           private val rateDAO: RateDAO
) {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDAO.replaceAll(webservice.getTransactions())
            rateDAO.replaceAll(webservice.getRates())
        }
    }

    /**
     * Gets transactions from database and updates values
     * @return livedata from database updated from webservice
     */
    fun getTransactions(): LiveData<List<Transaction>> = liveData(Dispatchers.IO) {
        emitSource(transactionDAO.getProducts())
    }

    /**
     * Gets product transactions from database
     * @return liveData from database
     */
    fun getTransactions(productId: String): LiveData<List<Transaction>> = liveData(Dispatchers.IO) {
        emitSource(transactionDAO.getProductTransactions(productId))
    }

    /**
     * Gets rates
     */
    suspend fun getRates() : List<Rate> {
        return rateDAO.getRates()
    }

}