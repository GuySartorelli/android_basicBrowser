package com.example.rallion.basicbrowser;

import android.webkit.URLUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

class URLParser {

    static String parse(String input) {
        input = input.trim();
        if (input.contains(" ") || !input.contains(".")) return parseQuery(input);

        String url = URLUtil.guessUrl(input);

        return url;
    }

    private static String parseQuery(String input){
        String url = "https://www.google.com/";
        try {
            String query = URLEncoder.encode(input, "UTF-8");
            url += String.format("search?q=%s&hl=%s", query, Locale.getDefault().getLanguage());
        } catch (UnsupportedEncodingException e) {/*never gonna happen*/}
        return url;
    }
}
