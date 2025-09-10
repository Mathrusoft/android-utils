package org.matools.androidutils

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.content.ContextCompat

/**
 * Utility class for device-related information and operations.
 */
object DeviceUtils {

    /**
     * Gets the device's unique Android ID.
     */
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    /**
     * Gets the device model name.
     */
    fun getDeviceModel(): String {
        return "${Build.MANUFACTURER} ${Build.MODEL}"
    }

    /**
     * Gets the Android version name.
     */
    fun getAndroidVersion(): String {
        return Build.VERSION.RELEASE
    }

    /**
     * Gets the Android API level.
     */
    fun getApiLevel(): Int {
        return Build.VERSION.SDK_INT
    }

    /**
     * Checks if the device is a tablet based on screen size.
     */
    fun isTablet(context: Context): Boolean {
        return (context.resources.configuration.screenLayout and 
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    /**
     * Gets the screen width in pixels.
     */
    fun getScreenWidth(context: Context): Int {
        val windowManager = ContextCompat.getSystemService(context, WindowManager::class.java)
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    /**
     * Gets the screen height in pixels.
     */
    fun getScreenHeight(context: Context): Int {
        val windowManager = ContextCompat.getSystemService(context, WindowManager::class.java)
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    /**
     * Gets the screen density.
     */
    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    /**
     * Converts dp to pixels.
     */
    fun dpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    /**
     * Converts pixels to dp.
     */
    fun pxToDp(context: Context, px: Float): Int {
        return (px / context.resources.displayMetrics.density).toInt()
    }

    /**
     * Checks if the device is in portrait orientation.
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    /**
     * Checks if the device is in landscape orientation.
     */
    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
}
