package com.gnb.gnbtrades.data.adapters

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

class JSONAdapter {
    @FromJson
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}