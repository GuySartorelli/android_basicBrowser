package com.example.rallion.basicbrowser;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class BasicBrowserWebChromeClient extends WebChromeClient {

    private LayoutController layoutController;

    public BasicBrowserWebChromeClient(LayoutController layoutController){
        super();
        this.layoutController = layoutController;
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        layoutController.setProgress(progress);
    }
}
