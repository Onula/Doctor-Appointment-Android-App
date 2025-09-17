package com.example.r3.Model;

import android.os.StrictMode;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DBManager {
    OkHttpClient client;

    private static String myIP = "localhost:8080";

    public DBManager(){
        client = new OkHttpClient();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    // Send the request, handle the response and return true if succes or false otherwise
    public boolean getResponse(String url) {
        Request req = buildRequest(url);
        try {
            Response response = client.newCall(req).execute();
            String responseData = response.body().string();
            if ( response.isSuccessful() ) {
                // Handle the successful response
                System.out.println("Response from server: " + responseData);
                if(responseData.equals("1")) return true;
                else return false;

            } else {
                // Handle the unsuccessful response
                System.out.println("Request failed with code: " + response.code());
            }
            response.close();
        } catch (IOException e) {
            // Handle the network failure or other errors
            e.printStackTrace();
        }
        return false;
    }

    // Send the request, handle the response as JSONArray
    public String getJSONDataResponse(String url) {
        String jsonData = null;
        Request req = buildRequest(url);
        Response response = null;
        try {
            response = client.newCall(req).execute();
            if (response.isSuccessful()) {
                // Handle the successful response
                jsonData = response.body().string();
                System.out.println("Response from server: " + jsonData);
                if(!jsonData.isEmpty()){
                    return jsonData;
                }else return null;

            } else {
                // Handle the unsuccessful response
                System.out.println("Request failed with code: " + response.code());
            }
            response.close();
        } catch (IOException e) {
            // Handle the network failure or other errors
            e.printStackTrace();
        }
        return jsonData;
    }

    //Build request
    private Request buildRequest(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static String getMyIP() {
        return myIP;
    }
}
