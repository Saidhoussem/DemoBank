package com.demo.banck.data

import java.text.SimpleDateFormat
import java.util.Date


object AppDateUtil {

     fun getDateTime(s: String): String {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}