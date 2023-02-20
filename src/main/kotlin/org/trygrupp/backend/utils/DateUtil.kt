package org.trygrupp.backend.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

object DateUtil {

    fun dateFormat(date: String?): Date? {
        var date1: Date? = null
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            date1 = format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    }

    fun dateToString(date: Date?): String {
        val format: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return format.format(date)
    }

    fun dateToJoinedString(date: Date?): String {
        val format: DateFormat = SimpleDateFormat("yyyyMMdd")
        return format.format(date)
    }

    fun dateTimeFullFormat(date: String): Date? {
        var date1: Date? = null
        val dateTime = "$date 00:00:00"
        val format: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            date1 = format.parse(dateTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    }

    fun atStartOfDay(date: Date): Date {
        val localDateTime = dateToLocalDateTime(date)
        val startOfDay = localDateTime.with(LocalTime.MIN)
        return localDateTimeToDate(startOfDay)
    }

    fun atEndOfDay(date: Date): Date {
        val localDateTime = dateToLocalDateTime(date)
        val endOfDay = localDateTime.with(LocalTime.MAX)
        return localDateTimeToDate(endOfDay)
    }

    fun dateToLocalDateTime(date: Date): LocalDateTime {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
    }

    private fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
    }

    fun todayDate(): Date? {
        val todayDate = Date()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val today = simpleDateFormat.format(todayDate)
        return dateTimeFullFormat(today)
    }

    fun calculateAge(year: Int, month: Int, day: Int): Int {
        val dob = LocalDate.of(year, month, day)
        val curDate = LocalDate.now()
        //calculates the amount of time between two dates and returns the years
        return Period.between(dob, curDate).years
    }

    fun isSameMonth(date1: Date?, date2: Date?): Boolean {
        val fmt = SimpleDateFormat("yyyyMM")
        return fmt.format(date1) == fmt.format(date2)
    }

    fun todayDateInAnyYear(yearDiff: Int): Date? {
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, yearDiff)
        val anyYear = cal.time
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val yearDate = simpleDateFormat.format(anyYear)
        return dateTimeFullFormat(yearDate)
    }

}