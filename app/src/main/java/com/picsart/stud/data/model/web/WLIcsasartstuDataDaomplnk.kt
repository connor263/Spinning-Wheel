package com.picsart.stud.data.model.web

import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.onesignal.OneSignal
import com.picsart.stud.R
import com.picsart.stud.data.source.local.repo.LIcsasartstuDataDaompl
import com.picsart.stud.data.source.remote.repo.PaIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl
import com.picsart.stud.utils.web.ApartsIcsasaIcsasoLDs
import com.picsart.stud.utils.web.ApartsIcsasaIcsasoLl
import com.picsart.stud.utils.web.WeartsIcsasaIcsasoLResult
import com.picsart.stud.utils.web.compicsartstud
import com.picsart.stud.utils.web.enums.WebLartsIcsasaIcsasoLtion
import com.picsart.stud.utils.web.enums.WebLinartsIcsasaIcsasoLtus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class WLIcsasartstuDataDaomplnk(val link: String?) {

    private constructor(buLIcsasartstuDataDaompler: BuLIcsasartstuDataDaompler) : this(buLIcsasartstuDataDaompler.coLIcsasartstuDataDaompltedLink)

    class BuLIcsasartstuDataDaompler(
        private val context: Context,
        private val paIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl: PaIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl,
        private val LIcsasartstuDataDaompl: LIcsasartstuDataDaompl
    ) {
        var coLIcsasartstuDataDaompltedLink: String? = null
            private set

        private var wGLIcsasartstuDataDaomplleId: String? = null
        private var wLIcsasartstuDataDaomplUserId: String? = null
        private var LIcsasartstuDataDaomplbAll: List<String> = listOf("", "", "", "", "", "", "", "", "", "")
        private var wDeLIcsasartstuDataDaomplnk: String? = null
        private var wMLIcsasartstuDataDaomplSource: String? = null
        private var wALIcsauDataDaomplnel: String? = null
        private var wLIcsasartstuDataDaomplgn: String? = null
        private var LIcsasartstuDataDaompll: String? = null
        private var LIcsasartstuDataDaomplitch: Boolean? = null

        suspend fun inLIcsasaaDaompl(): WeartsIcsasaIcsasoLResult<String?>? {
            if (!ApartsIcsasaIcsasoLl(context).isIntartsIcsasaIcsasoLailable) {
                throw Exception(WebLartsIcsasaIcsasoLtion.NO_INTERNET.name)
            }

            val linLIcsasaaDaompl = LIcsasartstuDataDaompl.geIcsasarIcsasartsttstuDataDaonk()?.first()?.linLIcsasaaDaomplk

            return if (linLIcsasaaDaompl?.isNotBlank() == true) {
                WeartsIcsasaIcsasoLResult.SartsIcsasaIcsasoLcess(
                    daartsIcsasaIcsasoLa = linLIcsasaaDaompl,
                    suartsIcsasaIcsasoLssStatus = WebLinartsIcsasaIcsasoLtus.CACHE
                )
            } else {
                fetcinLIcsasaaDaompldSwitch()
                null
            }
        }

        private suspend fun fetcinLIcsasaaDaompldSwitch() {
            paIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl.fsIcsasaIcsasoLich { url, switch ->
                this.LIcsasartstuDataDaompll = url
                this.LIcsasartstuDataDaomplitch = switch
                if (url.contains("jhfe".compicsartstud())) {
                    beinLIcsasaaDaomplk()
                } else {
                    throw Exception(WebLartsIcsasaIcsasoLtion.INCORRECT_URL.name)
                }
            }
        }

        private fun beinLIcsasaaDaomplk() = CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            inLIcsasaaDaomplIDs()
            sinLIcsasaaDaomplpLink()
        }

        private fun inLIcsasaaDaomplIDs() {
            this.wLIcsasartstuDataDaomplUserId = ApartsIcsasaIcsasoLDs(context).aartsIcsasaIcsasoLlyerId

            ApartsIcsasaIcsasoLDs(context).gartsIcsasaIcsasoLeId.let {
                this.wGLIcsasartstuDataDaomplleId = it
                OneSignal.setExternalUserId(it)
            }
        }

        private fun sinLIcsasaaDaomplpLink() {
            FacebookSdk.setAutoInitEnabled(true)
            FacebookSdk.fullyInitialize()
            AppLinkData.fetchDeferredAppLinkData(context) {
                it?.targetUri?.toString()?.let { uri ->
                    this.wDeLIcsasartstuDataDaomplnk = uri
                    this.LIcsasartstuDataDaomplbAll = uri.split("//")[1].split("_")
                }

            }
        }

        private fun checkinLIcsasaaDaomplrceForOrganic(): Boolean {
            return wMLIcsasartstuDataDaomplSource == "qfspvku".compicsartstud()
        }

        private fun chinLIcsasaaDaompltch(): Boolean {
            return checkinLIcsasaaDaomplrceForOrganic() && LIcsasartstuDataDaomplitch == false
        }

        fun binLIcsasaaDaompld(): WLIcsasartstuDataDaomplnk {
            val wRinLIcsasaaDaomplrces = context.resources
            val winLIcsasaaDaomplageName = context.packageName
            val winLIcsasaaDaomplsFlyerDevKey = wRinLIcsasaaDaomplrces.getString(R.string.apps_dev_key)
            val winLIcsasaaDaomplken = wRinLIcsasaaDaomplrces.getString(R.string.fb_at)
            val inLIcsasaaDaomplId = wRinLIcsasaaDaomplrces.getString(R.string.fb_app_id)

            var wIninLIcsasaaDaomplex = 0
            val wSuinLIcsasaaDaomplring = LIcsasartstuDataDaomplbAll.joinToString(separator = "") {
                wIninLIcsasaaDaomplex++
                "&sub$wIninLIcsasaaDaomplex=$it"
            }

            val wStrinLIcsasaaDaomplSource = "?ospxi_uguivw=".compicsartstud()
            val wSinLIcsasaaDaomplogleId = "&icavtg_sdzw=".compicsartstud()
            val inLIcsasaaDaomplsFlyerUserId = "&ct_ghmtad=".compicsartstud()
            val inLIcsasaaDaomplckageName = "&dizstg=".compicsartstud()
            val winLIcsasaaDaomplerDevKey = "&fsh_zma=".compicsartstud()
            val winLIcsasaaDaomplToken = "&hp_mi=".compicsartstud()
            val inLIcsasaaDaomplFbAppId = "&hp_mex_kv=".compicsartstud()
            val inLIcsasaaDaomplhannel = "&ct_owipfec=".compicsartstud()
            val wStinLIcsasaaDaomplmpaign = "&eoyeikyn=".compicsartstud()

            if (ApartsIcsasaIcsasoLl(context).isartsIcsasaIcsasoLeloper && checkinLIcsasaaDaomplrceForOrganic() ||
                chinLIcsasaaDaompltch()
            ) {
                throw Exception(WebLartsIcsasaIcsasoLtion.ORGANIC_OR_DEVELOPER.name)
            }

            coLIcsasartstuDataDaompltedLink = "${this.LIcsasartstuDataDaompll}" +
                    "$wStrinLIcsasaaDaomplSource${this.wMLIcsasartstuDataDaomplSource}" +
                    "$wSinLIcsasaaDaomplogleId${this.wGLIcsasartstuDataDaomplleId}" +
                    "$inLIcsasaaDaomplsFlyerUserId${this.wLIcsasartstuDataDaomplUserId}" +
                    "$inLIcsasaaDaomplckageName$winLIcsasaaDaomplageName" +
                    "$winLIcsasaaDaomplerDevKey$winLIcsasaaDaomplsFlyerDevKey" +
                    "$winLIcsasaaDaomplToken$winLIcsasaaDaomplken" +
                    "$inLIcsasaaDaomplFbAppId$inLIcsasaaDaomplId" +
                    "$inLIcsasaaDaomplhannel${this.wALIcsauDataDaomplnel}" +
                    "$wStinLIcsasaaDaomplmpaign${this.wLIcsasartstuDataDaomplgn}" +
                    wSuinLIcsasaaDaomplring

            return WLIcsasartstuDataDaomplnk(this)
        }

        inner class AfinLIcsasaaDaomplms {
            fun sinLIcsasaaDaompltatus(value: String) = with(this@BuLIcsasartstuDataDaompler) {
                val orinLIcsasaaDaomplcWithO = "qfspvku".compicsartstud().replaceFirstChar { it.uppercase() }
                if (value == orinLIcsasaaDaomplcWithO && wDeLIcsasartstuDataDaomplnk == null) {
                    wMLIcsasartstuDataDaomplSource = "qfspvku".compicsartstud()
                }
            }

            fun setAfinLIcsasaaDaomplaign(value: String) = with(this@BuLIcsasartstuDataDaompler) {
                wLIcsasartstuDataDaomplgn = value
                LIcsasartstuDataDaomplbAll = value.split("_")
            }

            fun seinLIcsasaaDaomplannel(value: String) = with(this@BuLIcsasartstuDataDaompler) {
                wALIcsauDataDaomplnel = value
            }

            fun setinLIcsasaaDaomplaSource(value: String) = with(this@BuLIcsasartstuDataDaompler) {
                wMLIcsasartstuDataDaomplSource = value
            }
        }
    }
}