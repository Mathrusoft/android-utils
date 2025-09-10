package org.matools.androidutils.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.matools.androidutils.*
import org.matools.androidutils.sample.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefsUtils: PrefsUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize PrefsUtils
        prefsUtils = PrefsUtils.getInstance(this, "sample_prefs")
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        // String Utils demos
        binding.btnValidateEmail.setOnClickListener {
            val email = binding.etTestEmail.text.toString()
            val isValid = StringUtils.isValidEmail(email)
            val result = "Email '$email' is ${if (isValid) "VALID" else "INVALID"}"
            appendResult("📧 Email Validation", result)
        }
        
        binding.btnProcessString.setOnClickListener {
            val text = binding.etTestString.text.toString()
            val results = mutableListOf<String>()
            
            results.add("Original: '$text'")
            results.add("Title Case: '${StringUtils.toTitleCase(text)}'")
            results.add("Truncated (10): '${StringUtils.truncate(text, 10)}'")
            results.add("Remove Accents: '${StringUtils.removeAccents(text)}'")
            results.add("Is Null/Empty: ${StringUtils.isNullOrEmpty(text)}")
            results.add("Is Null/Blank: ${StringUtils.isNullOrBlank(text)}")
            results.add("Random String (8): '${StringUtils.randomAlphanumeric(8)}'")
            
            appendResult("🔤 String Processing", results.joinToString("\n"))
        }
        
        // Device Utils demo
        binding.btnGetDeviceInfo.setOnClickListener {
            val results = mutableListOf<String>()
            
            results.add("Device Model: ${DeviceUtils.getDeviceModel()}")
            results.add("Android Version: ${DeviceUtils.getAndroidVersion()}")
            results.add("API Level: ${DeviceUtils.getApiLevel()}")
            results.add("Is Tablet: ${DeviceUtils.isTablet(this)}")
            results.add("Screen Width: ${DeviceUtils.getScreenWidth(this)}px")
            results.add("Screen Height: ${DeviceUtils.getScreenHeight(this)}px")
            results.add("Screen Density: ${DeviceUtils.getScreenDensity(this)}")
            results.add("Is Portrait: ${DeviceUtils.isPortrait(this)}")
            results.add("Is Landscape: ${DeviceUtils.isLandscape(this)}")
            results.add("16dp to px: ${DeviceUtils.dpToPx(this, 16f)}px")
            results.add("64px to dp: ${DeviceUtils.pxToDp(this, 64f)}dp")
            results.add("Device ID: ${DeviceUtils.getDeviceId(this).take(8)}...")
            
            appendResult("📱 Device Information", results.joinToString("\n"))
        }
        
        // Network Utils demo
        binding.btnCheckNetwork.setOnClickListener {
            val results = mutableListOf<String>()
            
            results.add("Is Connected: ${NetworkUtils.isConnected(this)}")
            results.add("WiFi Connected: ${NetworkUtils.isWifiConnected(this)}")
            results.add("Mobile Connected: ${NetworkUtils.isMobileConnected(this)}")
            results.add("Network Type: ${NetworkUtils.getNetworkType(this)}")
            
            appendResult("🌐 Network Status", results.joinToString("\n"))
        }
        
        // Date Utils demo
        binding.btnFormatDate.setOnClickListener {
            val now = Date()
            val results = mutableListOf<String>()
            
            results.add("Current Date: ${DateUtils.formatDate(now, DateUtils.FORMAT_DATE_DEFAULT)}")
            results.add("Display Format: ${DateUtils.formatDate(now, DateUtils.FORMAT_DATE_DISPLAY)}")
            results.add("Date & Time: ${DateUtils.formatDate(now, DateUtils.FORMAT_DATETIME_DEFAULT)}")
            results.add("Time Only: ${DateUtils.formatDate(now, DateUtils.FORMAT_TIME_DEFAULT)}")
            results.add("Timestamp: ${DateUtils.getCurrentTimestamp()}")
            results.add("Is Today: ${DateUtils.isToday(now)}")
            results.add("Time Ago: ${DateUtils.getTimeAgo(DateUtils.addHours(now, -2))}")
            results.add("7 Days Later: ${DateUtils.formatDate(DateUtils.addDays(now, 7))}")
            
            appendResult("📅 Date Formatting", results.joinToString("\n"))
        }
        
        // Preferences Utils demo
        binding.btnSavePreference.setOnClickListener {
            val timestamp = System.currentTimeMillis()
            prefsUtils.putString("sample_string", "Hello AndroidUtils!")
            prefsUtils.putInt("sample_int", 42)
            prefsUtils.putBoolean("sample_boolean", true)
            prefsUtils.putFloat("sample_float", 3.14f)
            prefsUtils.putLong("sample_long", timestamp)
            
            val result = "Saved preferences:\n" +
                    "• String: 'Hello AndroidUtils!'\n" +
                    "• Integer: 42\n" +
                    "• Boolean: true\n" +
                    "• Float: 3.14\n" +
                    "• Long: $timestamp"
            
            appendResult("💾 Save Preferences", result)
        }
        
        binding.btnLoadPreference.setOnClickListener {
            val results = mutableListOf<String>()
            
            val sampleString = prefsUtils.getString("sample_string", "Not found")
            val sampleInt = prefsUtils.getInt("sample_int", -1)
            val sampleBoolean = prefsUtils.getBoolean("sample_boolean", false)
            val sampleFloat = prefsUtils.getFloat("sample_float", 0.0f)
            val sampleLong = prefsUtils.getLong("sample_long", 0L)
            
            results.add("String: '$sampleString'")
            results.add("Integer: $sampleInt")
            results.add("Boolean: $sampleBoolean")
            results.add("Float: $sampleFloat")
            results.add("Long: $sampleLong")
            results.add("Contains 'sample_string': ${prefsUtils.contains("sample_string")}")
            results.add("Contains 'nonexistent': ${prefsUtils.contains("nonexistent")}")
            
            appendResult("📂 Load Preferences", results.joinToString("\n"))
        }
    }
    
    private fun appendResult(title: String, result: String) {
        val timestamp = DateUtils.formatDate(Date(), "HH:mm:ss")
        val formattedResult = "[$timestamp] $title\n$result\n" + "─".repeat(40) + "\n"
        
        val currentText = binding.tvResults.text.toString()
        val newText = if (currentText.contains("Click any button")) {
            formattedResult
        } else {
            formattedResult + currentText
        }
        
        binding.tvResults.text = newText
    }
}
