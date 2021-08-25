package com.maryang.theredhouse

import com.maryang.theredhouse.util.PriceUtil
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PriceUtilTest {

    @Test
    fun test1() {
        assertEquals("7백만원", PriceUtil.convertKoreanPriceUnit(7000000))
    }

    @Test
    fun test2() {
        assertEquals("1천만원", PriceUtil.convertKoreanPriceUnit(10000000))
    }

    @Test
    fun test3() {
        assertEquals("5천5백만원", PriceUtil.convertKoreanPriceUnit(55000000))
    }

    @Test
    fun test4() {
        assertEquals("10억원", PriceUtil.convertKoreanPriceUnit(1000000000))
    }

    @Test
    fun test5() {
        assertEquals("5억3백만원", PriceUtil.convertKoreanPriceUnit(503000000))
    }

    @Test
    fun test6() {
        assertEquals("15억5천만원", PriceUtil.convertKoreanPriceUnit(1550000000))
    }

    @Test
    fun test7() {
        assertEquals("12억4천5백만원", PriceUtil.convertKoreanPriceUnit(1245000000))
    }
}
