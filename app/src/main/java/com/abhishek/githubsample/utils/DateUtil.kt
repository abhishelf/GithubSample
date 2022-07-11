package com.abhishek.githubsample.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtil {

    companion object {
        fun getReadableDate(standardDate: String?): String {
            try {
                val inputFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
                val outputFormatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)
                val date: LocalDate = LocalDate.parse(standardDate, inputFormatter)
                return outputFormatter.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }

        fun mergeClosedCreateDate(createdAt: String?, closedAt: String?): String {
            val closedAtDate: String = DateUtil.getReadableDate(createdAt)
            val createdAtDate: String = DateUtil.getReadableDate(closedAt)
            return "Closed : $closedAtDate \u2022 Created : $createdAtDate"
        }
    }
}