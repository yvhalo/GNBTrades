package com.gnb.gnbtrades.domain.entities

/**
 * Rate represents exchange rate between two currencies
 * @param from source currency
 * @param to destination currency
 * @param rateVal exchange rate value
 */
data class Rate (val from: String, val to: String, val rateVal: Float)