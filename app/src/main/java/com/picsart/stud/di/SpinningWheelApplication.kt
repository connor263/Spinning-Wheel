package com.picsart.stud.di

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import com.picsart.stud.R
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SpinningWheelApplication : Application() {
    @Inject
    lateinit var appspinLIcsasaaDaompluveData: MutableLiveData<MutableMap<String, Any>>

    override fun onCreate() {
        super.onCreate()
        iadasvaadlsWMDaldmslmadv()
    }

    private fun iadasvaadlsWMDaldmslmadv() {
        AppsFlyerLib.getInstance()
            .init(
                getString(R.string.apps_dev_key),
                object : AppsFlyerConversionListener {
                    override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                        data?.let {
                            appspinLIcsasaaDaompluveData.postValue(it)
                        }
                    }

                    override fun onConversionDataFail(p0: String?) {}
                    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
                    override fun onAttributionFailure(p0: String?) {}
                }, this
            ).start(this)

        diafsdkamdkwRWMDWAVmdavsmdavmsDWVDWv()
    }

    private fun diafsdkamdkwRWMDWAVmdavsmdavmsDWVDWv() {
        OneSignal.initWithContext(this)
        OneSignal.setAppId(getString(R.string.one_signal_key))
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
    }
}