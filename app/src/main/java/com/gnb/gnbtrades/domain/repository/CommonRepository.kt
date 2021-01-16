package com.gnb.gnbtrades.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gnb.gnbtrades.data.Webservice
import com.gnb.gnbtrades.domain.entities.Rate
import com.gnb.gnbtrades.domain.entities.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * CommonRepository
 * Fetchs data from application dataSources.
 */

// TODO create RemoteDataSource
// TODO manage result options (Success, Error)
class CommonRepository @Inject constructor(private val webservice: Webservice) {

    /**
     * Gets all available rates
     * @return Observable with operation result
     */
    fun getRates() : LiveData<List<Rate>> {
        val data = MutableLiveData<List<Rate>>()
        webservice.getRates().enqueue(object: Callback<List<Rate>> {
            override fun onResponse(call: Call<List<Rate>>, response: Response<List<Rate>>) {
                data.value = response.body();
            }

            override fun onFailure(call: Call<List<Rate>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return data
    }

    /**
     * Gets all available transactions
     * @return Observable with operation result
     */
    fun getTransactions() : LiveData<List<Transaction>> {
        val data = MutableLiveData<List<Transaction>>()
        webservice.getTransactions().enqueue(object: Callback<List<Transaction>> {
            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                data.value = response.body();
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                Log.e("", t.message.toString())
            }
        })
        return data
    }
}