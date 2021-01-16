package com.gnb.gnbtrades.domain.entities

/**
 * Transaction. Represents a transaction made by GNB
 * @param sku represents the product id
 * @param amount the value of the transaction
 * @param currency the currency of the transaction
 */
data class Transaction(
    val sku : String,
    val amount: Float,
    val currency: String
)