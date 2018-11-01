package com.example.rallion.basicbrowser;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BasicBrowserWebViewClient extends WebViewClient {

    private LayoutController layoutController;

    BasicBrowserWebViewClient(LayoutController layoutController){
        super();
        this.layoutController = layoutController;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon){
        super.onPageStarted(view, url, favicon);
        layoutController.setLocation(url);
    }

}
