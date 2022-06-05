package com.picsart.stud.utils.web

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import javax.inject.Singleton

@Singleton
class AppUtil(private val context: Context) {
    val isInternetAvailable: Boolean
        get() {
            val connectivityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
                    ?: return false
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    val isDeveloper: Boolean = Settings.Secure.getInt(
        context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) == 1
}