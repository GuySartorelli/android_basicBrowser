package com.example.rallion.basicbrowser;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

class LayoutController {

    private AppCompatActivity activity;

    LayoutController(AppCompatActivity activity) {
        this.activity = activity;
    }

    void setupBrowserLayout(){
        activity.setContentView(R.layout.activity_main);
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        activity.setSupportActionBar(toolbar);

        WebView webView = activity.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        EditText locationInput = activity.findViewById(R.id.location_input);
        locationInput.setOnEditorActionListener((view, actionId, event) -> {
            webView.loadUrl(URLParser.parse(locationInput.getText().toString()));
            return true;
        });

    }
}
