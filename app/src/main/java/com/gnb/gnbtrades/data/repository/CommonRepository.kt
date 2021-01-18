package com.gnb.gnbtrades.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.gnb.gnbtrades.data.remote.Webservice
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.data.local.TransactionDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * CommonRepository
 * Fetch data from application dataSources.
 */

// TODO manage result options (Success, Error)
class CommonRepository @Inject constructor(private val webservice: Webservice,
                                           private val transactionDAO: TransactionDAO) {

    /**
     * Gets transactions from database and updates values
     * @return livedata from database updated from webservice
     */
    fun getTransactions(): LiveData<List<Transaction>> = liveData(Dispatchers.IO) {
        CoroutineScope(Dispatchers.IO).launch {
            transactionDAO.replaceAll(webservice.getTransactions())
        }
        emitSource(transactionDAO.getProducts())

    }
}