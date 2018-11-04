package com.example.rallion.basicbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences preferences = getSharedPreferences("com.example.rallion.basicbrowser", MODE_PRIVATE);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> historySet = preferences.getStringSet("history", new HashSet<String>());

        RecyclerView historyList = findViewById(R.id.history_list);
        historyList.setLayoutManager(new LinearLayoutManager(this));
        historyList.setAdapter(new RecyclerAdapter(this, historySet));

        TextView clearHistoryText = findViewById(R.id.clear_history);
        clearHistoryText.setOnClickListener((view) -> {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putStringSet("history", Collections.emptySet());
                editor.apply();
                historyList.swapAdapter(new RecyclerAdapter(this, Collections.emptySet()), false);
        });
        Log.d("DEBUG_LOG", "end of history oncreate");
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        setResult(RESULT_CANCELED, result);
        super.onBackPressed();
    }
}
