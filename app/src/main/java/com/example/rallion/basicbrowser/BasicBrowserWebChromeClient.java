package com.example.rallion.basicbrowser;

import android.graphics.Bitmap;
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

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        layoutController.setPageTitle(title);
//        if (layoutController.pageHasIcon()) layoutController.savePageInHistory();
        layoutController.savePageInHistory();
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
//        layoutController.setPageIcon(icon);
//        if (layoutController.pageHasTitle()) layoutController.savePageInHistory();
    }
}
