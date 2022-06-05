package com.picsart.stud.utils.web

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient


class AppIDs(private val context: Context) {
    val googleId: String
        get() {
            val id = AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
            Log.d("TAG", "setGoogleId: $id")
            return id

        }
    val appsFlyerId: String?
        get() {
            val id = AppsFlyerLib.getInstance().getAppsFlyerUID(context)
            Log.d("TAG", "setAppsFlyerID: $id")
            return id
        }
}