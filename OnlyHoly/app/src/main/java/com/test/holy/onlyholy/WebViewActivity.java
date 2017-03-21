package com.test.holy.onlyholy;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by houlin.jiang on 2017/3/10.
 */

public class WebViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url = this.getIntent().getStringExtra("URL");
        WebView webView = (WebView)findViewById(R.id.layout_webview);
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
