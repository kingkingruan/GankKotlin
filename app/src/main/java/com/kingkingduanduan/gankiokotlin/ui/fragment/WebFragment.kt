package com.kingkingduanduan.gankiokotlin.ui.fragment

import android.os.Build
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kingkingduanduan.gankiokotlin.R
import com.kingkingduanduan.gankiokotlin.ui.base.BaseFragment

/**
 * Created by ruanjinjing on 2017/6/5.
 */
class WebFragment : BaseFragment() {
    companion object {
        val EXTRA_URL = "extra_url"
    }

    lateinit var url: String
    lateinit var webView: WebView

    override fun getLayoutId(): Int {
        return R.layout.fragment_webview
    }

    override fun initView(view: View) {
        webView = view.findViewById(R.id.webView) as WebView
        val settings = webView.settings
        settings.domStorageEnabled = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.useWideViewPort = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.javaScriptEnabled = true
        settings.defaultTextEncodingName = "utf-8"
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.loadWithOverviewMode = true

        if (Build.VERSION.SDK_INT >= 21) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url?.startsWith("xindaijia") ?: false) {
                    return true
                }
                webView.loadUrl(url)
                return true
            }
        })
        url = arguments.getString(EXTRA_URL)
        webView.loadUrl(url)
    }

    override fun initData() {
    }
}