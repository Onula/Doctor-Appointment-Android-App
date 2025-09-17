package com.example.r3.Model;

import com.google.gson.Gson;

public class UserDBConnector {
    static DBManager dbMan = new DBManager();
    private static String myIP = dbMan.getMyIP();
    private static final String DOCTOR_PHP_SCRIPT_URL = "http://"+myIP+"/PHPDbServices/User.php";
    private String actionName;
    private String url;



    public User getUser(String username, String password){
        // Replacing with the current function name to understand the php which action to do
        actionName = "getUser";
        url = DOCTOR_PHP_SCRIPT_URL+
                "?action="+actionName+
                "&UserName="+username+
                "&Password="+password;

        // Build the insertPatient request { buildRequest(..) }
        // Send the request and handle the response { getResponse(..) }

        String jsonData = dbMan.getJSONDataResponse(url);// Return NULL or {User}
        if(jsonData != null){
            Gson gson = new Gson();
            User user = gson.fromJson(jsonData, User.class);
            return user;
        }
        return null;

    }
}
