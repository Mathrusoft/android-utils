# Android Utils Library

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

A comprehensive Android utility library that provides common functionality for Android applications. This library includes utilities for string operations, device information, network connectivity, preferences management, and date/time operations.

## Features

- 🔧 **StringUtils** - String manipulation and validation utilities
- 📱 **DeviceUtils** - Device information and screen utilities  
- 🌐 **NetworkUtils** - Network connectivity checks and information
- 💾 **PrefsUtils** - Simplified SharedPreferences management
- 📅 **DateUtils** - Date and time formatting and manipulation

## Installation

### Gradle (Module level)

Add this to your module's `build.gradle` file:

```gradle
dependencies {
    implementation 'org.matools:androidutils:0.0.1'
}
```

### Gradle Kotlin DSL

```kotlin
dependencies {
    implementation("org.matools:androidutils:0.0.1")
}
```

## Requirements

- **Minimum SDK**: 21 (Android 5.0)
- **Target SDK**: 36 (Android 15)
- **Kotlin**: 1.9.10+

## Usage

### StringUtils

```kotlin
import org.matools.androidutils.StringUtils

// Check if string is null or empty
val isEmpty = StringUtils.isNullOrEmpty(someString)

// Convert to title case
val titleCase = StringUtils.toTitleCase("hello world") // "Hello World"

// Validate email
val isValid = StringUtils.isValidEmail("user@example.com")

// Truncate string
val truncated = StringUtils.truncate("Long string here", 10) // "Long st..."

// Remove accents
val clean = StringUtils.removeAccents("café") // "cafe"

// Generate random string
val random = StringUtils.randomAlphanumeric(8)
```

### DeviceUtils

```kotlin
import org.matools.androidutils.DeviceUtils

// Get device information
val deviceModel = DeviceUtils.getDeviceModel()
val androidVersion = DeviceUtils.getAndroidVersion()
val apiLevel = DeviceUtils.getApiLevel()

// Screen utilities
val screenWidth = DeviceUtils.getScreenWidth(context)
val screenHeight = DeviceUtils.getScreenHeight(context)
val isTablet = DeviceUtils.isTablet(context)

// Unit conversion
val pixelValue = DeviceUtils.dpToPx(context, 16f)
val dpValue = DeviceUtils.pxToDp(context, 64f)

// Orientation
val isPortrait = DeviceUtils.isPortrait(context)
val isLandscape = DeviceUtils.isLandscape(context)
```

### NetworkUtils

```kotlin
import org.matools.androidutils.NetworkUtils

// Check connectivity
val isConnected = NetworkUtils.isConnected(context)
val isWifi = NetworkUtils.isWifiConnected(context)
val isMobile = NetworkUtils.isMobileConnected(context)

// Get network type
val networkType = NetworkUtils.getNetworkType(context) // "WiFi", "Mobile", etc.
```

### PrefsUtils

```kotlin
import org.matools.androidutils.PrefsUtils

// Initialize (singleton pattern)
val prefs = PrefsUtils.getInstance(context)

// Store values
prefs.putString("username", "john_doe")
prefs.putInt("user_id", 12345)
prefs.putBoolean("is_logged_in", true)

// Retrieve values
val username = prefs.getString("username")
val userId = prefs.getInt("user_id", -1)
val isLoggedIn = prefs.getBoolean("is_logged_in", false)

// Other operations
prefs.remove("old_key")
prefs.clear()
val hasKey = prefs.contains("username")
```

### DateUtils

```kotlin
import org.matools.androidutils.DateUtils
import java.util.Date

// Format dates
val formatted = DateUtils.formatDate(Date(), DateUtils.FORMAT_DATE_DISPLAY)
val timestamp = DateUtils.formatTimestamp(System.currentTimeMillis())

// Parse dates
val date = DateUtils.parseDate("2023-12-25", DateUtils.FORMAT_DATE_DEFAULT)

// Current date/time
val currentDate = DateUtils.getCurrentDate()
val currentTimestamp = DateUtils.getCurrentTimestamp()

// Date comparisons
val isToday = DateUtils.isToday(someDate)
val isYesterday = DateUtils.isYesterday(someDate)

// Date calculations
val daysDiff = DateUtils.getDaysDifference(startDate, endDate)
val futureDate = DateUtils.addDays(Date(), 7)

// Human readable time
val timeAgo = DateUtils.getTimeAgo(someDate) // "2h ago", "3d ago", etc.
```

## Permissions

The library uses the following permissions (automatically included):

```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

## Sample App

Check out the `app` module in this repository for example usage of all utility classes.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

### Development Setup

1. Clone the repository
2. Copy `local.properties.template` to `local.properties` and update the Android SDK path
3. Open the project in Android Studio
4. Build and run tests

## Testing

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## Publishing

This library can be published to Maven Central or GitHub Packages. See the publishing configuration in the `build.gradle.kts` file.

## License

```
Copyright 2025 MA Tools

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

## Changelog

### Version 0.0.1
- Initial development release
- StringUtils with common string operations
- DeviceUtils for device information and screen utilities
- NetworkUtils for connectivity checks
- PrefsUtils for SharedPreferences management
- DateUtils for date/time operations

## Support

If you find this library helpful, please consider giving it a star ⭐ on GitHub!

For questions or support, please open an issue on GitHub: https://github.com/Mathrusoft/android-utils/issues
