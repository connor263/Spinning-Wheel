package com.picsart.stud.utils.web

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient


class ApartsIcsasaIcsasoLDs(private val context: Context) {
    val gartsIcsasaIcsasoLeId: String
        get() {
            return AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()

        }
    val aartsIcsasaIcsasoLlyerId: String?
        get() {
            return AppsFlyerLib.getInstance().getAppsFlyerUID(context)
        }
}