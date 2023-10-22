package com.demo.banck.data

import org.junit.Assert.*
import org.junit.Test

class AppDateUtilTest
{
    @Test
    fun right_behaviour() {
        val date = AppDateUtil.getDateTime("1644870724")
        assertEquals(date, "02/14/2022")
    }

    @Test
    fun wrong_behaviour() {
        val date = AppDateUtil.getDateTime("1644870724")
        assertNotEquals(date, "02/14/2023")
    }
}