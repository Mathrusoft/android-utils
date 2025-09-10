package org.matools.androidutils

import org.junit.Assert.*
import org.junit.Test

class StringUtilsTest {

    @Test
    fun testIsNullOrEmpty() {
        assertTrue(StringUtils.isNullOrEmpty(null))
        assertTrue(StringUtils.isNullOrEmpty(""))
        assertFalse(StringUtils.isNullOrEmpty("test"))
        assertFalse(StringUtils.isNullOrEmpty(" "))
    }

    @Test
    fun testIsNullOrBlank() {
        assertTrue(StringUtils.isNullOrBlank(null))
        assertTrue(StringUtils.isNullOrBlank(""))
        assertTrue(StringUtils.isNullOrBlank(" "))
        assertTrue(StringUtils.isNullOrBlank("   "))
        assertFalse(StringUtils.isNullOrBlank("test"))
    }

    @Test
    fun testToTitleCase() {
        assertEquals("", StringUtils.toTitleCase(null))
        assertEquals("", StringUtils.toTitleCase(""))
        assertEquals("Hello World", StringUtils.toTitleCase("hello world"))
        assertEquals("Test String", StringUtils.toTitleCase("TEST STRING"))
        assertEquals("Mixed Case String", StringUtils.toTitleCase("mIxEd CaSe StRiNg"))
    }

    @Test
    fun testTruncate() {
        assertEquals("", StringUtils.truncate(null, 10))
        assertEquals("", StringUtils.truncate("", 10))
        assertEquals("short", StringUtils.truncate("short", 10))
        assertEquals("this is...", StringUtils.truncate("this is a long string", 10))
        assertEquals("test---", StringUtils.truncate("test string", 7, "---"))
    }

    @Test
    fun testRemoveAccents() {
        assertEquals("", StringUtils.removeAccents(null))
        assertEquals("", StringUtils.removeAccents(""))
        assertEquals("cafe", StringUtils.removeAccents("café"))
        assertEquals("naieve", StringUtils.removeAccents("naïeve"))
        assertEquals("resume", StringUtils.removeAccents("résumé"))
    }

    @Test
    fun testIsValidEmail() {
        assertFalse(StringUtils.isValidEmail(null))
        assertFalse(StringUtils.isValidEmail(""))
        assertFalse(StringUtils.isValidEmail("invalid"))
        assertFalse(StringUtils.isValidEmail("invalid@"))
        assertFalse(StringUtils.isValidEmail("@invalid.com"))
        assertTrue(StringUtils.isValidEmail("test@example.com"))
        assertTrue(StringUtils.isValidEmail("user.name@domain.co.uk"))
    }

    @Test
    fun testRandomAlphanumeric() {
        val random1 = StringUtils.randomAlphanumeric(10)
        val random2 = StringUtils.randomAlphanumeric(10)
        
        assertEquals(10, random1.length)
        assertEquals(10, random2.length)
        assertNotEquals(random1, random2) // Very unlikely to be the same
        
        // Check that all characters are alphanumeric
        assertTrue(random1.all { it.isLetterOrDigit() })
        assertTrue(random2.all { it.isLetterOrDigit() })
    }
}
