package com.example.demo.Algorithm;


import com.google.gson.Gson;
// import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class GeoJSONParser {
    public static JsonObject parseGeoJSON(String filePath) {
        String geoJsonData = readFile(filePath);
        Gson gson = new Gson();
        return gson.fromJson(geoJsonData, JsonObject.class);
    }
    
    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
