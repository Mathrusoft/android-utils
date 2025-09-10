package org.matools.androidutils

import android.content.Context
import android.content.SharedPreferences

/**
 * Utility class for SharedPreferences operations.
 */
class PrefsUtils private constructor(context: Context, prefsName: String = "app_prefs") {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    
    companion object {
        @Volatile
        private var INSTANCE: PrefsUtils? = null
        
        fun getInstance(context: Context, prefsName: String = "app_prefs"): PrefsUtils {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: PrefsUtils(context.applicationContext, prefsName).also { INSTANCE = it }
            }
        }
    }
    
    /**
     * Stores a string value.
     */
    fun putString(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }
    
    /**
     * Retrieves a string value.
     */
    fun getString(key: String, defaultValue: String? = null): String? {
        return prefs.getString(key, defaultValue)
    }
    
    /**
     * Stores an integer value.
     */
    fun putInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }
    
    /**
     * Retrieves an integer value.
     */
    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }
    
    /**
     * Stores a boolean value.
     */
    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }
    
    /**
     * Retrieves a boolean value.
     */
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }
    
    /**
     * Stores a float value.
     */
    fun putFloat(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }
    
    /**
     * Retrieves a float value.
     */
    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return prefs.getFloat(key, defaultValue)
    }
    
    /**
     * Stores a long value.
     */
    fun putLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }
    
    /**
     * Retrieves a long value.
     */
    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return prefs.getLong(key, defaultValue)
    }
    
    /**
     * Removes a key-value pair.
     */
    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }
    
    /**
     * Clears all stored preferences.
     */
    fun clear() {
        prefs.edit().clear().apply()
    }
    
    /**
     * Checks if a key exists.
     */
    fun contains(key: String): Boolean {
        return prefs.contains(key)
    }
}
