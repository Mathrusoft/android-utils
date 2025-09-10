package org.matools.androidutils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Utility class for date and time operations.
 */
object DateUtils {

    const val FORMAT_DATE_DEFAULT = "yyyy-MM-dd"
    const val FORMAT_DATETIME_DEFAULT = "yyyy-MM-dd HH:mm:ss"
    const val FORMAT_TIME_DEFAULT = "HH:mm:ss"
    const val FORMAT_DATE_DISPLAY = "MMM dd, yyyy"
    const val FORMAT_DATETIME_DISPLAY = "MMM dd, yyyy HH:mm"

    /**
     * Formats a date with the specified pattern.
     */
    fun formatDate(date: Date, pattern: String = FORMAT_DATE_DEFAULT): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    }

    /**
     * Formats a timestamp with the specified pattern.
     */
    fun formatTimestamp(timestamp: Long, pattern: String = FORMAT_DATETIME_DEFAULT): String {
        return formatDate(Date(timestamp), pattern)
    }

    /**
     * Parses a date string with the specified pattern.
     */
    fun parseDate(dateString: String, pattern: String = FORMAT_DATE_DEFAULT): Date? {
        return try {
            SimpleDateFormat(pattern, Locale.getDefault()).parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Gets the current date as a formatted string.
     */
    fun getCurrentDate(pattern: String = FORMAT_DATE_DEFAULT): String {
        return formatDate(Date(), pattern)
    }

    /**
     * Gets the current timestamp in milliseconds.
     */
    fun getCurrentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    /**
     * Checks if a date is today.
     */
    fun isToday(date: Date): Boolean {
        val today = Calendar.getInstance()
        val dateToCheck = Calendar.getInstance().apply { time = date }
        
        return today.get(Calendar.YEAR) == dateToCheck.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == dateToCheck.get(Calendar.DAY_OF_YEAR)
    }

    /**
     * Checks if a date is yesterday.
     */
    fun isYesterday(date: Date): Boolean {
        val yesterday = Calendar.getInstance().apply { 
            add(Calendar.DAY_OF_YEAR, -1) 
        }
        val dateToCheck = Calendar.getInstance().apply { time = date }
        
        return yesterday.get(Calendar.YEAR) == dateToCheck.get(Calendar.YEAR) &&
                yesterday.get(Calendar.DAY_OF_YEAR) == dateToCheck.get(Calendar.DAY_OF_YEAR)
    }

    /**
     * Gets the difference between two dates in days.
     */
    fun getDaysDifference(startDate: Date, endDate: Date): Long {
        val diffInMillis = endDate.time - startDate.time
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
    }

    /**
     * Gets the difference between two dates in hours.
     */
    fun getHoursDifference(startDate: Date, endDate: Date): Long {
        val diffInMillis = endDate.time - startDate.time
        return TimeUnit.HOURS.convert(diffInMillis, TimeUnit.MILLISECONDS)
    }

    /**
     * Gets the difference between two dates in minutes.
     */
    fun getMinutesDifference(startDate: Date, endDate: Date): Long {
        val diffInMillis = endDate.time - startDate.time
        return TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS)
    }

    /**
     * Adds days to a date.
     */
    fun addDays(date: Date, days: Int): Date {
        val calendar = Calendar.getInstance().apply { 
            time = date
            add(Calendar.DAY_OF_YEAR, days)
        }
        return calendar.time
    }

    /**
     * Adds hours to a date.
     */
    fun addHours(date: Date, hours: Int): Date {
        val calendar = Calendar.getInstance().apply { 
            time = date
            add(Calendar.HOUR_OF_DAY, hours)
        }
        return calendar.time
    }

    /**
     * Gets a human-readable time ago string.
     */
    fun getTimeAgo(date: Date): String {
        val now = Date()
        val diffInMillis = now.time - date.time
        
        return when {
            diffInMillis < TimeUnit.MINUTES.toMillis(1) -> "Just now"
            diffInMillis < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis)
                "${minutes}m ago"
            }
            diffInMillis < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis)
                "${hours}h ago"
            }
            diffInMillis < TimeUnit.DAYS.toMillis(7) -> {
                val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
                "${days}d ago"
            }
            else -> formatDate(date, FORMAT_DATE_DISPLAY)
        }
    }
}
