package com.example.rallion.basicbrowser;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
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
        EditText locationInput = activity.findViewById(R.id.location_input);
        locationInput.setOnEditorActionListener((view, actionId, event) -> {
            webView.loadUrl(URLParser.parse(locationInput.getText().toString()));
            return true;
        });

    }
}
