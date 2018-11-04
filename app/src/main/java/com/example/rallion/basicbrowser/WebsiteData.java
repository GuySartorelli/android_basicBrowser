package com.example.rallion.basicbrowser;

//bitmap encoding/decoding from https://stackoverflow.com/questions/34662614/saving-an-object-containing-bitmap

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class WebsiteData implements Comparable<WebsiteData> {

    private String url;
    private String shortUrl;
    private String title;
//    private Bitmap favicon;
    private String timeStamp;

    public WebsiteData(String url) {
        this.url = url;
        this.shortUrl = url.contains("//") ? url.split("//")[1].split("/")[0] : url.split("/")[0];
        SimpleDateFormat formatter = new SimpleDateFormat("E d MMM yyyy:hhmmss");
        timeStamp = formatter.format(new Date());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.shortUrl = url.contains("//") ? url.split("//")[1].split("/")[0] : url.split("/")[0];
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public Bitmap getFavicon() {
////        byte[] imageAsBytes = Base64.decode(favicon.getBytes(), Base64.DEFAULT);
////        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//        return favicon;
//    }

//    public void setFavicon(Bitmap favicon) {
////        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
////        favicon.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
////        byte[] bytes = byteStream.toByteArray();
////        this.favicon = Base64.encodeToString(bytes, Base64.DEFAULT);
//        this.favicon = favicon;
//    }

    public String getTimeStamp() {
        return timeStamp.split(":")[0];
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void save(SharedPreferences prefs){
        Set<String> history = prefs.getStringSet("history", new HashSet<String>());
        history.add(this.toJson());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("history", history);
        editor.apply();
//        editor.commit();
        Log.d("DEBUG_LOG", "saved "+this.toJson());
    }

    @Override
    public int compareTo(WebsiteData other) {
        return 0-timeStamp.compareTo(other.timeStamp);
    }
}
