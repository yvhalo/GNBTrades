package com.gnb.gnbtrades.domain.usecase

import android.icu.util.Currency
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.data.entities.Transaction
import com.gnb.gnbtrades.data.repository.CommonRepository
import com.gnb.gnbtrades.domain.entities.Product
import com.gnb.gnbtrades.domain.entities.ProductTrades
import com.gnb.gnbtrades.domain.entities.Trade
import kotlinx.coroutines.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import java.util.Locale.filter
import javax.inject.Inject

class TradesUseCase @Inject constructor(private val commonRepository: CommonRepository) {

    private lateinit var exchangeRates : List<Rate>

    companion object {
        const val EURO = "EUR"
    }

    init {
        GlobalScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.Default) {
                commonRepository.getRates()
            }
            exchangeRates = result
        }
    }

    fun getTrades(productId: String): LiveData<ProductTrades>  {

        var trades =  Transformations.map(commonRepository.getTransactions(productId)) { transactions ->
            transactions.map { transaction ->
                if (transaction?.currency == EURO) {
                    transaction.amount?.let { createTradeObject(transaction.amount) }
                } else {
                    getEuroValue(transaction)?.let { euroAmount ->
                        createTradeObject(euroAmount)
                    }
                }
            }
        }

        var mediatorLiveData = MediatorLiveData<ProductTrades>()
        mediatorLiveData.addSource(trades) { trades ->
            var totalAmount = trades.sumByFloat { it.amount }
            mediatorLiveData.value = ProductTrades(totalAmount = String.format("%.2f %s", totalAmount, EURO), trades = trades)
        }

        return mediatorLiveData
    }

    fun createTradeObject(amount: Float) : Trade = Trade(amount, EURO)

    fun getEuroValue(transaction: Transaction) : Float? {
        return if (transaction.amount != null && transaction.currency != null) {
            filter(transaction, transaction.currency, transaction.amount)
        } else {0F}
    }

    fun filter(transaction: Transaction, currency: String, transactionValue: Float) : Float {
        var value = 0F
        var currencyRates = exchangeRates.filter { it.from == currency }

        for (rate in currencyRates) {
            if (rate.to == EURO) return transaction.amount!! * rate.rate
        }
        if (value == 0F) {
            var i = 0
            do {
                var rate = currencyRates[i]
                i++
                value = filter(transaction, rate.to, transactionValue * rate.rate)
            } while(value == 0F)
        }

        return value
    }

    inline fun <T>List<T?>.sumByFloat(selector: (T) -> Float): Float {
        var sum = 0F
        for (element in this) {
            element?.let{
                sum += selector(element)
            }
        }
        return sum
    }

}
