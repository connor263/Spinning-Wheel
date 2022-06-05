package com.picsart.stud.ui.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
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
    Log.d("TAG", "WebScreen: $link")
    val state = rememberWebViewState(url = link)
    val navigator = rememberWebViewNavigator()

    val fileData by remember {
        mutableStateOf<ValueCallback<Uri>?>(
            null
        )
    }
    var filePath by remember {
        mutableStateOf<ValueCallback<Array<Uri>>?>(
            null
        )
    }

    fun processResult(data: Intent?) {
        if (fileData == null && filePath == null) return
        var resultData: Uri? = null
        var resultPath: Array<Uri>? = null
        data?.let {
            resultData = it.data
            resultPath = arrayOf(Uri.parse(it.dataString))
        }
        fileData?.onReceiveValue(resultData)
        filePath?.onReceiveValue(resultPath)
    }


    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) processResult(it.data)
        }

    WebView(
        state = state,
        navigator = navigator,
        captureBackPresses = false,
        onCreated = {
            initWebView(it)
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
                filePath = filePathCallback
                Intent(Intent.ACTION_GET_CONTENT).run {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "kamvm/*".compicsartstud()
                    startForResult.launch(this)
                }
                return true
            }
        })

    BackHandler {
        if (state.loadingState is LoadingState.Finished && navigator.canGoBack ||
            state.isLoading
        ) {
            navigator.navigateBack()
        }
    }
}

@Suppress("DEPRECATION")
@SuppressLint("SetJavaScriptEnabled")
fun initWebView(webView: WebView) =
    with(webView.settings) {
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

        initCookie(webView)
    }

fun initCookie(webView: WebView) {
    webView.clearCache(false)
    CookieManager.getInstance().setAcceptCookie(true)
    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
}