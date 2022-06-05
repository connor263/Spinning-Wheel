package com.picsart.stud.utils.web

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import javax.inject.Singleton

@Singleton
class ApartsIcsasaIcsasoLl(private val context: Context) {
    val isIntartsIcsasaIcsasoLailable: Boolean
        get() {
            val conartsIcsasaIcsasoLityManager =
                ContextCompat.getSystemService(context, ConnectivityManager::class.java)
                    ?: return false
            val netartsIcsasaIcsasoLInfo = conartsIcsasaIcsasoLityManager.activeNetworkInfo
            return netartsIcsasaIcsasoLInfo != null && netartsIcsasaIcsasoLInfo.isConnected
        }
    val isartsIcsasaIcsasoLeloper: Boolean = Settings.Secure.getInt(
        context.contentResolver,
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
        0
    ) == 1
}