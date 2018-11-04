package com.example.rallion.basicbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private LayoutController layout;
    private SharedPreferences sPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sPreferences = getSharedPreferences("com.example.rallion.basicbrowser", MODE_PRIVATE);
//        sPreferences = getSharedPreferences("com.example.rallion.basicbrowser", MODE_WORLD_READABLE); //MODE_PRIVATE
//        sPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        layout = new LayoutController(this);
        layout.setupBrowserLayout();
        if (savedInstanceState != null) {
            layout.restoreWebView(savedInstanceState.getBundle("webView"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.forward:
                layout.goForward();
                return true;
//            case R.id.bookmarks:
//
//                return true;
            case R.id.history:
                Intent showHistory = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivityForResult(showHistory, 1);
                return true;
            case R.id.refresh:
                layout.refresh();
                return true;
//            case R.id.find:
//                layout.find();
//                return true;
//            case R.id.action_settings:
//                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            layout.goTo(data.getStringExtra("url"));
        }
    }

    @Override
    public void onBackPressed() {
        if (layout.canGoBack()){
            layout.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public SharedPreferences getPreferences() {
        return sPreferences;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("webView", layout.saveWebView());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) layout.restoreWebView(savedInstanceState.getBundle("webView"));
    }
}
