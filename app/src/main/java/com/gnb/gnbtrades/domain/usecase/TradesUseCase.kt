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
import java.math.BigDecimal
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

    /**
     * Initializes exchangeRates data once
     */
    init {
        GlobalScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.Default) {
                commonRepository.getRates()
            }
            exchangeRates = result
        }
    }

    /**
     * Gets trades from commonRepository and transforms to Domain-Presentation entities
     */
    fun getTrades(productId: String): LiveData<ProductTrades>  {

        var trades =  Transformations.map(commonRepository.getTransactions(productId)) { transactions ->
            transactions.map { transaction ->
                if (transaction.currency == EURO) {
                    transaction.amount?.let { createTradeObject(transaction.amount) }
                } else {
                    getEuroValue(transaction)?.let { euroAmount ->
                        createTradeObject(euroAmount)
                    }
                }
            }
        }

        var mediatorLiveData = MediatorLiveData<ProductTrades>()
        mediatorLiveData.addSource(trades) { processedTrades ->
            var totalAmount = processedTrades.sumByBigDecimal { it.amount }
            mediatorLiveData.value = ProductTrades(totalAmount = totalAmount, trades = processedTrades)
        }

        return mediatorLiveData
    }

    /**
     * Creates a Trade object from Transaction information
     * @amount transaction amount
     * @return trade with amount transformed into BigDecimal with Bankers Rounding
     */
    fun createTradeObject(amount: BigDecimal) : Trade = Trade(amount.setScale(2, RoundingMode.HALF_EVEN).toString(), EURO)

    /**
     * Gets euro conversion of the transaction amount
     * @param transaction
     * @return euro amount
     */
    fun getEuroValue(transaction: Transaction) : BigDecimal? {
        return if (transaction.amount != null && transaction.currency != null) {
            filter(transaction, transaction.currency, transaction.amount)
        } else {BigDecimal.ZERO}
    }

    /**
     * Filters currency exchange list and finds conversions from every required currency to euro until gets the right transformed amount
     * @param transaction to be transformed
     * @param currency currency to transform into euro
     * @param transactionValue current transaction value to transform into euro
     * @return euro amount
     */
    fun filter(transaction: Transaction, currency: String, transactionValue: BigDecimal) : BigDecimal {
        var value = BigDecimal.ZERO
        var currencyRates = exchangeRates.filter { it.from == currency }

        for (rate in currencyRates) {
            if (rate.to == EURO) return transaction.amount!! * rate.rate
        }
        if (value == BigDecimal.ZERO) {
            var i = 0
            do {
                var rate = currencyRates[i]
                i++
                value = filter(transaction, rate.to, transactionValue * rate.rate)
            } while(value == BigDecimal.ZERO)
        }

        return value
    }

    /**
     * extension function for List (Trades). adds all transaction amount values into a total
     */
    inline fun <T>List<T?>.sumByBigDecimal(selector: (T) -> String): String {
        var sum = BigDecimal.ZERO
        for (element in this) {
            element?.let{
                sum += BigDecimal(selector(element))
            }
        }
        sum.setScale(2, RoundingMode.HALF_EVEN)
        return sum.toString()
    }

}
