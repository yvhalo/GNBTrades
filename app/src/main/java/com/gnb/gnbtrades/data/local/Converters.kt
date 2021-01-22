package com.gnb.gnbtrades.data.local

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun fromStringToBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(value) }
    }

    @TypeConverter
    fun fromBigDecimalToString(value: BigDecimal?): String? {
        return value?.toString()
    }
}