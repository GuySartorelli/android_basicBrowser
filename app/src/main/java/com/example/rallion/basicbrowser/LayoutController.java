package com.example.rallion.basicbrowser;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;

class LayoutController {

    private AppCompatActivity activity;
    private WebView webView;
    private EditText locationInput;
    private ProgressBar progressBar;

    LayoutController(AppCompatActivity activity) {
        this.activity = activity;
    }

    void setupBrowserLayout(){
        activity.setContentView(R.layout.activity_main);
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        activity.setSupportActionBar(toolbar);
        progressBar = activity.findViewById(R.id.progressBar);
        setProgressVisibility(false);

        webView = activity.findViewById(R.id.web_view);
        webView.setWebViewClient(new BasicBrowserWebViewClient(this));
        webView.setWebChromeClient(new BasicBrowserWebChromeClient(this));
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
        locationInput.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus){
//                locationInput.selectAll();
                Drawable right;
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Drawable left = activity.getResources().getDrawable(R.drawable.ic_search_black_24dp); //locationInput.getCompoundDrawablesRelative()[0];
                    right = activity.getResources().getDrawable(R.drawable.delete);
                    locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(left, null, right, null);
                } else {
                    Drawable left = activity.getResources().getDrawable(R.drawable.ic_search_black_24dp); //locationInput.getCompoundDrawables()[0];
                    right = activity.getResources().getDrawable(R.drawable.delete);
                    locationInput.setCompoundDrawablesWithIntrinsicBounds(left, null, right, null);
                }
                double rightWidth = right.getBounds().width();
                locationInput.setOnTouchListener((v, event) -> {
                    if(event.getAction() == MotionEvent.ACTION_UP) {
                        if(event.getRawX() >= (locationInput.getRight() - rightWidth)) {
                            locationInput.setText("");
                            return true;
                        }
                    }
                    v.performClick();
                    return false;
                });

            } else setLocation(webView.getUrl()); //lost focus
        });

        SwipeRefreshLayout swiper = activity.findViewById(R.id.swipeContainer);
        swiper.setOnRefreshListener(() -> {
            webView.reload();
            swiper.setRefreshing(false);
        });
    }

    void setLocation(String url) {
        locationInput.setText(url);
        setLocationDrawable(url);
    }

    void setLocationDrawable(String url) {
        if (url.startsWith("https://")){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.secure), null, null, null);
            } else {
                locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.secure), null, null, null);
            }
        } else if (url.startsWith("http://")){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.insecure), null, null, null);
            } else {
                locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.insecure), null, null, null);
            }
        } else {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                locationInput.setCompoundDrawablesRelativeWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.error), null, null, null);
            } else {
                locationInput.setCompoundDrawablesWithIntrinsicBounds(activity.getResources().getDrawable(R.drawable.error), null, null, null);
            }
        }
    }

    boolean canGoBack(){
        return webView.canGoBack();
    }

    boolean canGoForward(){
        return webView.canGoForward();
    }

    void goBack(){
        if (canGoBack()) webView.goBack();
    }

    void goForward(){
        if (canGoForward()) webView.goForward();
    }

    void refresh(){
        webView.reload();
    }

    void setProgressVisibility(boolean visible) {
        progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    void setProgress(int progress) {
        progressBar.setProgress(progress);
    }
}
