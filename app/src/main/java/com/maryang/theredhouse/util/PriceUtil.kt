package com.maryang.theredhouse.util

object PriceUtil {

    fun convertKoreanPriceUnit(price: Long): String {
        val priceOrigin = price / 1000000 // 백만
        val c = priceOrigin % 10 //백만
        val b = (priceOrigin / 10) % 10 // 천만
        val a = priceOrigin / 100 // 억

        var priceString = ""
        if (a > 0) priceString += "${a}억"
        if (b > 0) priceString += "${b}천"
        if (c > 0) priceString += "${c}백"
        if (b > 0 || c > 0) priceString += "만"
        return priceString + "원"
    }
}
