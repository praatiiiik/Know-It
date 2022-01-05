package com.example.knowit.ui.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.knowit.R
import com.example.knowit.databinding.ActivityMainBinding
import com.example.knowit.databinding.ActivityWebViewBinding
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast


class WebViewActivity : AppCompatActivity() {

    private lateinit var webBinding: ActivityWebViewBinding
    private lateinit var webview : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(webBinding.root)

        val url : String? = intent.extras?.getString(KEY)

        webview = webBinding.webView

        webview.webViewClient = WebViewClient()

        if (url != null) {
            webview.loadUrl(url)
        } else {
            Toast.makeText(this,"SomeThing gone Wrong :(",Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        private const val KEY = "URL"

        fun getStartIntent(
            context: Context,
            uniqueId: String
        ) = Intent(context, WebViewActivity::class.java).apply {
            putExtra(KEY, uniqueId)
        }
    }
}