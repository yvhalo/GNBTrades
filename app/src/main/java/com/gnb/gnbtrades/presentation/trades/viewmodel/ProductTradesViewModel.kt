package com.gnb.gnbtrades.presentation.trades.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gnb.gnbtrades.data.entities.Rate
import com.gnb.gnbtrades.domain.entities.ProductTrades
import com.gnb.gnbtrades.domain.usecase.TradesUseCase

/**
 * ViewModel for ProductsTradesFragment
 */
class ProductTradesViewModel @ViewModelInject constructor(
        private val tradesUseCase: TradesUseCase
) : ViewModel() {

    /**
     * Gets trades by product
     */
    fun getTrades(productId: String) : LiveData<ProductTrades> {

        return tradesUseCase.getTrades(productId)

    }
}