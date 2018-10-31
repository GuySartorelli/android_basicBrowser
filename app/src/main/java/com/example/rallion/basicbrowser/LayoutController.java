package com.example.rallion.basicbrowser;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        EditText locationInput = activity.findViewById(R.id.location_input);
        WebView webView = activity.findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        Button browseBtn = activity.findViewById(R.id.browse_btn);
        browseBtn.setOnClickListener((view) -> {
            webView.loadUrl(locationInput.getText().toString());
        });

    }
}
