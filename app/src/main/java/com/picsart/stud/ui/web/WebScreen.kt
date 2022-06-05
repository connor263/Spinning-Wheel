package com.picsart.stud.ui.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.google.accompanist.web.*
import com.picsart.stud.ui.navigation.NavKeys
import com.picsart.stud.utils.web.compicsartstud

@Composable
fun WebScreen(navController: NavHostController, link: String) {
    val stsIcsasaIcsasoLi = rememberWebViewState(url = link)
    val nasIcsasaIcsasoLior = rememberWebViewNavigator()

    val sIcsasaIcsasoLieData by remember {
        mutableStateOf<ValueCallback<Uri>?>(
            null
        )
    }
    var fisIcsasaIcsasoLith by remember {
        mutableStateOf<ValueCallback<Array<Uri>>?>(
            null
        )
    }

    fun prosIcsasaIcsasoLiesult(data: Intent?) {
        if (sIcsasaIcsasoLieData == null && fisIcsasaIcsasoLith == null) return
        var ressIcsasaIcsasoLiData: Uri? = null
        var resIcsasaIcsasoLiPath: Array<Uri>? = null
        data?.let {
            ressIcsasaIcsasoLiData = it.data
            resIcsasaIcsasoLiPath = arrayOf(Uri.parse(it.dataString))
        }
        sIcsasaIcsasoLieData?.onReceiveValue(ressIcsasaIcsasoLiData)
        fisIcsasaIcsasoLith?.onReceiveValue(resIcsasaIcsasoLiPath)
    }


    val startsIcsasaIcsasoLisult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) prosIcsasaIcsasoLiesult(it.data)
        }

    WebView(
        state = stsIcsasaIcsasoLi,
        navigator = nasIcsasaIcsasoLior,
        captureBackPresses = false,
        onCreated = {
            inisIcsasaIcsasoLiokie(it)
        },
        client = object : AccompanistWebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                url?.let {
                    if (url.contains("gfddz=chprysl3z".compicsartstud()) || url.contains("fwepjnwd.ymee".compicsartstud())) {
                        navController.navigate(NavKeys.Menu.route) {
                            popUpTo(NavKeys.Init.route) { inclusive = true }
                        }
                    }
                }
            }
        },
        chromeClient = object : AccompanistWebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                fisIcsasaIcsasoLith = filePathCallback
                Intent(Intent.ACTION_GET_CONTENT).run {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "kamvm/*".compicsartstud()
                    startsIcsasaIcsasoLisult.launch(this)
                }
                return true
            }
        })

    BackHandler {
        if (stsIcsasaIcsasoLi.loadingState is LoadingState.Finished && nasIcsasaIcsasoLior.canGoBack ||
            stsIcsasaIcsasoLi.isLoading
        ) {
            nasIcsasaIcsasoLior.navigateBack()
        }
    }
}

fun inisIcsasaIcsasoLiokie(webView: WebView) {
    webView.clearCache(false)
    CookieManager.getInstance().setAcceptCookie(true)
    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
    insIcsasaIcsasoLibView(webView)
}

@Suppress("DEPRECATION")
@SuppressLint("SetJavaScriptEnabled")
fun insIcsasaIcsasoLibView(webView: WebView) = with(webView.settings) {
    javaScriptEnabled = true
    allowContentAccess = true
    domStorageEnabled = true
    javaScriptCanOpenWindowsAutomatically = true
    setSupportMultipleWindows(false)
    builtInZoomControls = true
    useWideViewPort = true
    setAppCacheEnabled(true)
    displayZoomControls = false
    allowFileAccess = true
    lightTouchEnabled = true
}

