package com.gnb.gnbtrades.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Transaction. Represents a transaction made by GNB
 * @param id used by database as index
 * @param sku represents the product id
 * @param amount the value of the transaction
 * @param currency the currency of the transaction
 */

@Entity(tableName = "transaction")
data class Transaction constructor(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val sku : String,
    val amount: Float? = 0F,
    val currency: String? = ""
)