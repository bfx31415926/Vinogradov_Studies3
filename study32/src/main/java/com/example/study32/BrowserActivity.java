package com.example.study32;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class BrowserActivity extends AppCompatActivity {
    final String TAG = "MyTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        Uri data = getIntent().getData();
        Log.d(TAG, "BrowserActivity: onCreate()");
        webView.loadUrl(data.toString());
    }
}