package com.gnb.gnbtrades.domain.entities

import com.gnb.gnbtrades.data.entities.Transaction
import java.math.BigDecimal
import java.time.temporal.TemporalAmount

/**
 * ProductTrades.
 */
data class ProductTrades(val totalAmount: String, val trades: List<Trade?>)