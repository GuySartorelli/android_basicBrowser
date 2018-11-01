package com.example.rallion.basicbrowser;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;

class LayoutController {

    private AppCompatActivity activity;
    private WebView webView;
    private EditText locationInput;

    LayoutController(AppCompatActivity activity) {
        this.activity = activity;
    }

    void setupBrowserLayout(){
        activity.setContentView(R.layout.activity_main);
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        activity.setSupportActionBar(toolbar);

        webView = activity.findViewById(R.id.web_view);
        webView.setWebViewClient(new BasicBrowserWebViewClient(this));
//        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        locationInput = activity.findViewById(R.id.location_input);
        locationInput.setOnEditorActionListener((view, actionId, event) -> {
            webView.loadUrl(URLParser.parse(locationInput.getText().toString()));
            InputMethodManager keyboard = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
            webView.requestFocus();
            return true;
        });
    }

    void setLocation(String url){
        locationInput.setText(url);

        if (url.startsWith("https://")){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.secure), null, null, null);
            }
            locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.secure), null, null, null);
        } else if (url.startsWith("http://")){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.insecure), null, null, null);
            }
            locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.insecure), null, null, null);
        } else {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.error), null, null, null);
            }
            locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.error), null, null, null);
        }
    }

    public boolean canGoBack(){
        return webView.canGoBack();
    }

    public void goBack(){
        if (canGoBack()) webView.goBack();
    }
}
