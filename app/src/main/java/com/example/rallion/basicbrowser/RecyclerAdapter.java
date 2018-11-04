package com.example.rallion.basicbrowser;
//adapted from https://developer.android.com/guide/topics/ui/layout/recyclerview#java

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView rowTitle;
        public TextView rowUrl;
        public TextView rowDate;
//        public ImageView rowIcon;
        public View row;
        public RecyclerViewHolder(View view) {
            super(view);
            rowTitle = view.findViewById(R.id.row_title);
            rowUrl = view.findViewById(R.id.row_url);
            rowDate = view.findViewById(R.id.row_date);
//            rowIcon = view.findViewById(R.id.history_favicon);
            row = view.findViewById(R.id.row);
        }
    }

    private Activity activity;
    private List<WebsiteData> listItems;
    private Set<String> dates;

    public RecyclerAdapter(Activity activity, Set<String> historySet) {
        this.activity = activity;
        dates = new HashSet<String>();
        listItems = new ArrayList<WebsiteData>();
        Gson gson = new Gson();
        for (String item : historySet) listItems.add(gson.fromJson(item, WebsiteData.class));
        Collections.sort(listItems);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // create a new view
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(rowLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int i) {
        WebsiteData row = listItems.get(i);
//        if (!dates.contains(row.getTimeStamp())){
            holder.rowDate.setText(row.getTimeStamp());
//            dates.add(row.getTimeStamp());
//        } else {
//            ((ViewManager)holder.rowDate.getParent()).removeView(holder.rowDate);
//        }
        holder.rowTitle.setText(row.getTitle());
        holder.rowUrl.setText(row.getShortUrl());
//        holder.rowIcon.setImageBitmap(row.getFavicon());
        holder.row.setOnClickListener((view) -> {
            Intent result = new Intent();
            result.putExtra("url", row.getUrl());
            activity.setResult(activity.RESULT_OK, result);
            activity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

}
