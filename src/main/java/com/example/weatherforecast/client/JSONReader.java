package com.example.weatherforecast.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONReader {

    private String readAll(Reader rd) throws IOException {
        var sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    protected JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            var rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            var jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }


}
