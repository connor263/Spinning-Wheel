package com.picsart.stud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.picsart.stud.data.model.web.LiinLIcsasaaDaompldel
import com.picsart.stud.data.model.web.WLIcsasartstuDataDaomplnk
import com.picsart.stud.data.source.local.repo.LIcsasartstuDataDaompl
import com.picsart.stud.data.source.remote.repo.PaIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl
import com.picsart.stud.ui.navigation.NavKeys
import com.picsart.stud.ui.navigation.SpinningWheelContent
import com.picsart.stud.ui.theme.SpinningWheelTheme
import com.picsart.stud.utils.web.WeartsIcsasaIcsasoLResult
import com.picsart.stud.utils.web.compicsartstud
import com.picsart.stud.utils.web.enums.WebLartsIcsasaIcsasoLtion
import com.picsart.stud.utils.web.enums.WebLinartsIcsasaIcsasoLtus
import com.picsart.stud.utils.web.wertstuDataDaomnkCall
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var paIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl: PaIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl

    @Inject
    lateinit var LIcsasartstuDataDaompl: LIcsasartstuDataDaompl

    @Inject
    lateinit var appsFlyrtstuDataDaomerLiveData: MutableLiveData<MutableMap<String, Any>>

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpinningWheelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val navController = rememberNavController()
                    SpinningWheelContent(navController = navController, mainViewModel = viewModel)
                }
            }
        }

        irtstuDataDaomApp()
    }

    fun irtstuDataDaomApp() = lifecycleScope.launch(Dispatchers.Main) {
        creatertstuDataDaomink().let { result ->
            when (result) {
                is WeartsIcsasaIcsasoLResult.SartsIcsasaIcsasoLcess -> {
                    result.dartsIcsasaIcsasoLta?.let { link ->

                        if (link.isNotBlank()) {
                            navigateToWeb(link)

                            if (result.succartsIcsasaIcsasoLStatus == WebLinartsIcsasaIcsasoLtus.COLLECT) {
                                LIcsasartstuDataDaompl.sIcsasaIcsastuDataDaooLink(LiinLIcsasaaDaompldel(linLIcsasaaDaomplk = link))
                            }
                        }
                    }
                }
                is WeartsIcsasaIcsasoLResult.EartsIcsasaIcsasoLor -> {
                    when (result.meartsIcsasaIcsasoLage) {
                        WebLartsIcsasaIcsasoLtion.NO_INTERNET -> viewModel.isrtstuDataDaomding = false

                        WebLartsIcsasaIcsasoLtion.INCORRECT_URL,
                        WebLartsIcsasaIcsasoLtion.ORGANIC_OR_DEVELOPER -> navigateToMenu()
                        null -> {}
                    }
                }
            }
        }
    }

    private suspend fun creatertstuDataDaomink() = wertstuDataDaomnkCall {
        viewModel.isrtstuDataDaomding = true
        val buLIcsasartstuDataDaompler = WLIcsasartstuDataDaomplnk.BuLIcsasartstuDataDaompler(
            this,
            paIuDataDaotIcsasartstuDataDaortstuDataDaoryImpl,
            LIcsasartstuDataDaompl
        ).apply {
            inLIcsasaaDaompl()?.let {
                return@wertstuDataDaomnkCall it as WeartsIcsasaIcsasoLResult.SartsIcsasaIcsasoLcess<String?>
            }
        }

        callbackFlow<WeartsIcsasaIcsasoLResult<String?>> {
            appsFlyrtstuDataDaomerLiveData.observe(this@MainActivity) {
                for ((key, value) in it) {
                    when (key) {
                        "ct_eiivms".compicsartstud() -> buLIcsasartstuDataDaompler.AfinLIcsasaaDaomplms().sinLIcsasaaDaompltatus(value.toString())
                        "eoyeikyn".compicsartstud() -> buLIcsasartstuDataDaompler.AfinLIcsasaaDaomplms().setAfinLIcsasaaDaomplaign(value.toString())
                        "ospxi_uguivw".compicsartstud() -> buLIcsasartstuDataDaompler.AfinLIcsasaaDaomplms().setinLIcsasaaDaomplaSource(value.toString())
                        "ct_owipfec".compicsartstud() -> buLIcsasartstuDataDaompler.AfinLIcsasaaDaomplms().seinLIcsasaaDaomplannel(value.toString())
                    }
                }

                trySend(
                    wertstuDataDaomnkCall {
                        val brtstuDataDaomdLink = buLIcsasartstuDataDaompler.binLIcsasaaDaompld()
                        WeartsIcsasaIcsasoLResult.SartsIcsasaIcsasoLcess(
                            daartsIcsasaIcsasoLa = brtstuDataDaomdLink.link,
                            suartsIcsasaIcsasoLssStatus = WebLinartsIcsasaIcsasoLtus.COLLECT
                        )
                    }
                )
                close()
            }
            awaitClose { cancel() }
        }.first()
    }

    private fun navigateToMenu() {
        viewModel.route = NavKeys.Menu.route
    }

    private fun navigateToWeb(link: String) {
        val encode = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
        viewModel.route = NavKeys.Web(encode).route
    }
}