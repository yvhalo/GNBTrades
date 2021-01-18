package com.gnb.gnbtrades.domain.entities

import com.gnb.gnbtrades.data.entities.Transaction
import java.time.temporal.TemporalAmount

data class ProductTrades(val totalAmount: String, val trades: List<Trade?>)