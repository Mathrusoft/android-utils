package org.matools.androidutils

import android.text.Html
import android.text.Spanned
import java.text.Normalizer
import java.util.Locale

/**
 * Utility class for string operations commonly used in Android apps.
 */
object StringUtils {

    /**
     * Checks if a string is null or empty.
     */
    fun isNullOrEmpty(str: String?): Boolean {
        return str.isNullOrEmpty()
    }

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     */
    fun isNullOrBlank(str: String?): Boolean {
        return str.isNullOrBlank()
    }

    /**
     * Capitalizes the first letter of each word in a string.
     */
    fun toTitleCase(str: String?): String {
        if (str.isNullOrBlank()) return ""
        
        return str.split(" ").joinToString(" ") { word ->
            word.lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }

    /**
     * Converts HTML string to Spanned text for TextView display.
     */
    @Suppress("DEPRECATION")
    fun fromHtml(html: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    /**
     * Truncates a string to a specified length and adds ellipsis if truncated.
     */
    fun truncate(str: String?, maxLength: Int, ellipsis: String = "..."): String {
        if (str.isNullOrEmpty() || str.length <= maxLength) return str ?: ""
        return str.substring(0, maxLength - ellipsis.length) + ellipsis
    }

    /**
     * Removes accents and diacritical marks from a string.
     */
    fun removeAccents(str: String?): String {
        if (str.isNullOrEmpty()) return ""
        return Normalizer.normalize(str, Normalizer.Form.NFD)
            .replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    }

    /**
     * Validates if a string is a valid email address.
     */
    fun isValidEmail(email: String?): Boolean {
        if (email.isNullOrBlank()) return false
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     * Generates a random alphanumeric string of specified length.
     */
    fun randomAlphanumeric(length: Int): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}
