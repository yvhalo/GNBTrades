package com.gnb.gnbtrades.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Rate represents exchange rate between two currencies
 * @param id used by database as index
 * @param from source currency
 * @param to destination currency
 * @param rateVal exchange rate value
 */

@Entity(tableName = "rate")
data class Rate constructor(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val from: String?,
    val to: String?,
    val rateVal: Float?)